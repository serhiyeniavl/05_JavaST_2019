package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.User;
import by.training.info_system.entity.status.OrderStatus;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.CarService;
import by.training.info_system.service.OrderService;
import by.training.info_system.service.UserService;
import by.training.info_system.util.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmptyCommand extends Command {
    private static final int URL_TIMEOUT = 2;

    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String requestedPage = (String) request.getAttribute("requestedPage");
        JspPage page = PageFactory.defineAndGet(PageEnum.valueOf(requestedPage.toUpperCase()));

        updateBlackList();

        if ((page.getUri().equals(PageEnum.SIGNIN.getUri())
                || page.getUri().equals(PageEnum.SIGNUP.getUri()))) {
            return handleSignInPage(request, page);
        }

        if (page.getUri().equals(PageEnum.CARS.getUri())) {
            loadCars(request);
            if (checkRequestMessageAttrs(request)) {
                return processMessageAttrs(request, page);
            }
        }

        if (page.getUri().equals(PageEnum.USERS.getUri())) {
            return handleUsersPage(request, page);
        }

        if (page.getUri().equals(PageEnum.PROFILE.getUri())) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            loadProfileInfo(request, user.getId());
            if (checkRequestMessageAttrs(request)) {
                return processMessageAttrs(request, page);
            }
            return page;
        }

        if (page.getUri().equals(PageEnum.ORDERS.getUri())
                || page.getUri().equals(PageEnum.MY_ORDERS.getUri())) {
            return handleOrdersPage(request, page);
        }
        return page;
    }

    private JspPage handleSignInPage(final HttpServletRequest request,
                                     JspPage page) {
        if (checkRequestMessageAttrs(request)) {
            return processMessageAttrs(request, page);
        } else if (checkSecureAttr(request)) {
            return processSecureAttr(request, page);
        } else if (isAuthorizedUserTrySignAgain(request, page)) {
            page = PageFactory.defineAndGet(PageEnum.HOME);
            page.setRedirect(true);
            return page;
        }
        return page;
    }

    private JspPage handleOrdersPage(final HttpServletRequest request,
                                     final JspPage page) {
        loadOrderStatuses(request);
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        updateOrdersStatus();
        if (request.getParameter("order_id") != null) {
            long id;
            try {
                id = Long.parseLong(request.getParameter("order_id"));
            } catch (Exception e) {
                page.setRedirect(true);
                return page;
            }
            List<Order> order = null;
            Order o;
            if (page.getUri().equals(PageEnum.ORDERS.getUri())) {
                o = service.findOrderById(id).orElse(null);
            } else {
                User user = (User) request.getSession(false)
                        .getAttribute("user");
                o = service.findOrder(user.getId(), id).orElse(null);
            }
            if (o != null) {
                order = new ArrayList<>();
                order.add(o);
            }
            putAttrInRequest(request, RequestAttribute.ORDERS, order);
            if (checkRequestMessageAttrs(request)
                    && checkOrderIdMsg(request)) {
                return processOrderIdAttr(request, processMessageAttrs(request, page));
            }
        } else {
            int pageNum = 1;
            String showOrders;
            if (request.getParameter(RequestParameter.PAGE.getValue()) != null
                    && request.getParameter(RequestParameter.SHOW.getValue()) != null) {
                pageNum = Integer.parseInt(request.getParameter(RequestParameter.PAGE.getValue()));
                showOrders = request.getParameter(RequestParameter.SHOW.getValue());
                putAttrInRequest(request, RequestAttribute.CURRENT_PAGE, pageNum);
            } else {
                appendRequestParameterWithoutEncoding(page, RequestParameter.PAGE,
                        String.valueOf(pageNum));
                appendRequestParameterWithoutEncoding(page, RequestParameter.SHOW,
                        OrderStatus.ALL.getValue());
                page.setRedirect(true);
                return page;
            }
            showOrders = showOrders.replace(' ', '_');
            OrderStatus status = OrderStatus.valueOf(showOrders.toUpperCase());
            if (page.getUri().equals(PageEnum.ORDERS.getUri())) {
                putAttrInRequest(request, RequestAttribute.SHOW, showOrders);
                switch (status) {
                    case DENIED:
                        loadDeniedOrders(request, pageNum);
                        break;
                    case CONFIRMED:
                        loadConfirmedOrders(request, pageNum);
                        break;
                    case EXPIRED:
                        loadOverdueOrders(request, pageNum);
                        break;
                    case COMPLETED:
                        loadCompletedOrders(request, pageNum);
                        break;
                    case NOT_CONFIRMED:
                        loadNotConfirmedOrders(request, pageNum);
                        break;
                    case ACTIVE:
                        loadActiveOrders(request, pageNum);
                        break;
                    case EXTENDED:
                        loadExtendedOrders(request, pageNum);
                        break;
                    case ACCEPTED:
                        loadAcceptedOrders(request, pageNum);
                        break;
                    default:
                        loadAllOrders(request, pageNum);
                        break;
                }
                if (checkRequestMessageAttrs(request)
                        && checkOrderIdMsg(request)) {
                    return processOrderIdAttr(request, processMessageAttrs(request, page));
                }
            } else {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("user");
                putAttrInRequest(request, RequestAttribute.SHOW, showOrders);
                long userId = user.getId();
                switch (status) {
                    case DENIED:
                        loadDeniedUserOrders(request, userId, pageNum);
                        break;
                    case CONFIRMED:
                        loadConfirmedUserOrders(request, userId, pageNum);
                        break;
                    case EXPIRED:
                        loadOverdueUserOrders(request, userId, pageNum);
                        break;
                    case ACCEPTED:
                        loadAcceptedUserOrders(request, userId, pageNum);
                        break;
                    case EXTENDED:
                        loadExtendedUserOrders(request, userId, pageNum);
                        break;
                    case ACTIVE:
                        loadActiveUserOrders(request, userId, pageNum);
                        break;
                    case NOT_CONFIRMED:
                        loadNotConfirmedUserOrders(request, userId, pageNum);
                        break;
                    case COMPLETED:
                        loadCompletedUserOrders(request, userId, pageNum);
                        break;
                    default:
                        loadUserOrders(request, userId, pageNum);
                        break;
                }
                if (checkRequestMessageAttrs(request)
                        && checkOrderIdMsg(request)) {
                    return processOrderIdAttr(request, processMessageAttrs(request, page));
                }
            }
        }
        return page;
    }

    private JspPage handleUsersPage(final HttpServletRequest request,
                                    final JspPage page) {
        int pageNum = 1;
        if (request.getParameter(RequestParameter.PAGE.getValue()) != null) {
            if (request.getParameter(RequestParameter.SHOW.getValue()) == null) {
                appendRequestParameterWithoutEncoding(page, RequestParameter.SHOW,
                        RequestAttribute.ALL_USERS.getValue());
                page.setRedirect(true);
                return page;
            }
            pageNum = Integer.parseInt(request.getParameter(RequestParameter.PAGE.getValue()));
            putAttrInRequest(request, RequestAttribute.CURRENT_PAGE, pageNum);
            if (request.getParameter(RequestParameter.SHOW.getValue())
                    .equals(RequestAttribute.ALL_USERS.getValue())) {
                loadUsers(request, pageNum);
            } else if (request.getParameter(RequestParameter.SHOW.getValue())
                    .equals(RequestAttribute.MANAGERS.getValue())) {
                loadManagers(request, pageNum);
            } else if (request.getParameter(RequestParameter.SHOW.getValue())
                    .equals(RequestAttribute.CUSTOMERS.getValue())) {
                loadCustomers(request, pageNum);
            } else if (request.getParameter(RequestParameter.SHOW.getValue())
                    .equals(RequestAttribute.BLACK_LIST.getValue())) {
                loadBlackList(request, pageNum);
            }
            return page;
        } else if (request.getParameter(RequestParameter.ID.getValue()) != null) {
            long id;
            try {
                id = Long.valueOf(request.getParameter("id"));
            } catch (Exception e) {
                appendRequestParameterWithoutEncoding(page, RequestParameter.SHOW,
                        RequestAttribute.ALL_USERS.getValue());
                page.setRedirect(true);
                return page;
            }
            loadUserById(request, id);
            return page;
        } else if (request.getParameter(RequestParameter.EMAIL.getValue()) != null) {
            loadUserByEmail(request, request.getParameter("email"));
            return page;
        }
        appendRequestParameterWithoutEncoding(page, RequestParameter.SHOW,
                RequestAttribute.ALL_USERS.getValue());
        appendRequestParameterWithoutEncoding(page, RequestParameter.PAGE,
                String.valueOf(pageNum));
        page.setRedirect(true);
        return page;
    }

    private boolean isAuthorizedUserTrySignAgain(final HttpServletRequest request,
                                                 final JspPage page) {
        return (request.getSession(false) != null
                && request.getSession(false).getAttribute("user") != null
                && (page.getUri().equals(PageEnum.SIGNIN.getUri())
                || page.getUri().equals((PageEnum.SIGNUP.getUri()))));
    }

    private boolean checkSecureAttr(final HttpServletRequest request) {
        return request.getParameter(RequestParameter.SECURITY.getValue()) != null
                && request.getParameter(RequestParameter.TIME.getValue()) != null;
    }

    private boolean checkRequestMessageAttrs(final HttpServletRequest request) {
        return request.getParameter(RequestParameter.MESSAGE.getValue()) != null
                && request.getParameter(RequestParameter.TIME.getValue()) != null;
    }

    private boolean checkOrderIdMsg(final HttpServletRequest request) {
        return request.getParameter(RequestParameter.ORDER_ID.getValue()) != null;
    }

    private JspPage processMessageAttrs(final HttpServletRequest request,
                                        final JspPage page) {
        String time = Encoder.decodeString(request.getParameter(
                RequestParameter.TIME.getValue()));
        LocalDateTime reqTime = LocalDateTime.parse(
                time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (reqTime.plusSeconds(URL_TIMEOUT).isAfter(LocalDateTime.now())) {
            String msg = Encoder.decodeString(request.getParameter(
                    RequestParameter.MESSAGE.getValue()));
            String attr = Encoder.decodeString(request.getParameter(
                    RequestParameter.ATTRIBUTE.getValue())).toUpperCase();
            putAttrInRequest(request, RequestAttribute.valueOf(attr),
                    msg);
        } else {
            if (request.getParameter(RequestParameter.PAGE.getValue()) != null) {
                appendRequestParameterWithoutEncoding(page, RequestParameter.PAGE,
                        request.getParameter(RequestParameter.PAGE.getValue()));
            }
            page.setRedirect(true);
        }
        return page;
    }

    private JspPage processSecureAttr(final HttpServletRequest request,
                                      final JspPage page) {
        String time = Encoder.decodeString(request.getParameter(
                RequestParameter.TIME.getValue()));
        LocalDateTime reqTime = LocalDateTime.parse(
                time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (reqTime.plusSeconds(URL_TIMEOUT).isAfter(LocalDateTime.now())) {
            String msg = Encoder.decodeString(request.getParameter(
                    RequestParameter.SECURITY.getValue()));
            String attr = Encoder.decodeString(request.getParameter(
                    RequestParameter.ATTRIBUTE.getValue())).toUpperCase();
            putAttrInRequest(request, RequestAttribute.valueOf(attr),
                    msg);
        } else {
            page.setRedirect(true);
        }
        return page;
    }

    private JspPage processOrderIdAttr(final HttpServletRequest request,
                                       final JspPage page) {
        String orderId = Encoder.decodeString(request.getParameter(
                RequestParameter.ORDER_ID.getValue()));
        putAttrInRequest(request,
                RequestAttribute
                        .valueOf(RequestParameter.ORDER_ID
                                .toString()), orderId);
        return page;
    }

    private void loadCars(final HttpServletRequest request) {
        CarService service = factory.getService(CarService.class).orElseThrow();
        List<Car> cars = service.loadCars().orElseGet(ArrayList::new);
        putAttrInRequest(request, RequestAttribute.CARS, cars);
    }

    private void loadAllOrders(final HttpServletRequest request, final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findAllOrders(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countOrders() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadOverdueOrders(final HttpServletRequest request,
                                   final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findOverdue(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countOverdue() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadNotConfirmedOrders(final HttpServletRequest request,
                                        final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findNotConfirmed(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countNotConfirmed() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadConfirmedOrders(final HttpServletRequest request,
                                     final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findConfirmed(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countConfirmed() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadAcceptedOrders(final HttpServletRequest request,
                                    final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findAccepted(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countAccepted() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadActiveOrders(final HttpServletRequest request,
                                  final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findActive(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countActive() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadExtendedOrders(final HttpServletRequest request,
                                    final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findExtended(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countExtended() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadCompletedOrders(final HttpServletRequest request,
                                     final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findCompleted(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countCompleted() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadDeniedOrders(final HttpServletRequest request,
                                  final int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findDenied(pageNum, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countDenied() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);

    }

    private void loadProfileInfo(final HttpServletRequest request,
                                 final long userId) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        User user = service.findById(userId).orElse(User.builder().build());
        putAttrInRequest(request, RequestAttribute.PROFILE, user);
    }

    private void loadOrderStatuses(final HttpServletRequest request) {
        putAttrInRequest(request, RequestAttribute.ORDER_STATUS, OrderStatus.values());
    }

    private void loadUserOrders(final HttpServletRequest request, final long id,
                                final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findUserOrders(id, page, ORDERS_PER_PAGE)
                .orElseGet(ArrayList::new);
        int numOfPages = (int) Math.ceil(service.countOrders(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadOverdueUserOrders(final HttpServletRequest request, final long id,
                                       final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findOverdue(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countOverdue(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadActiveUserOrders(final HttpServletRequest request, final long id,
                                      final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findActive(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countActive(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadAcceptedUserOrders(final HttpServletRequest request, final long id,
                                        final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findAccepted(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countAccepted(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadNotConfirmedUserOrders(final HttpServletRequest request, final long id,
                                            final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findNotConfirmed(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countNotConfirmed(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadConfirmedUserOrders(final HttpServletRequest request, final long id,
                                         final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findConfirmed(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countConfirmed(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadExtendedUserOrders(final HttpServletRequest request, final long id,
                                        final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findExtended(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countExtended(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadCompletedUserOrders(final HttpServletRequest request, final long id,
                                         final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findCompleted(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countCompleted(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadDeniedUserOrders(final HttpServletRequest request, final long id,
                                      final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findDenied(id, page, ORDERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countDenied(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadUsers(final HttpServletRequest request, final int page) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<User> users = service.findAll(page, USERS_PER_PAGE).orElse(null);
        int numOfPages = (int) Math.ceil(service.countUsers() * 1.0 / USERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.USERS_LIST, users);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadUserById(final HttpServletRequest request, final long id) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        User user = service.findById(id).orElse(null);
        List<User> findingUser = null;
        if (user != null) {
            findingUser = new ArrayList<>();
            findingUser.add(user);
        }
        putAttrInRequest(request, RequestAttribute.USERS_LIST, findingUser);
    }

    private void loadUserByEmail(final HttpServletRequest request, final String email) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        User user = service.findByEmailFullInto(email).orElse(null);
        List<User> findingUser = null;
        if (user != null) {
            findingUser = new ArrayList<>();
            findingUser.add(user);
        }
        putAttrInRequest(request, RequestAttribute.USERS_LIST, findingUser);
    }

    private void loadManagers(final HttpServletRequest request, final int page) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        int numOfPages = (int) Math.ceil(service.countUsers() * 1.0 / USERS_PER_PAGE);
        List<User> users = service.findManagers(page, USERS_PER_PAGE)
                .orElse(null);
        putAttrInRequest(request, RequestAttribute.USERS_LIST, users);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadCustomers(final HttpServletRequest request, final int page) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        int numOfPages = (int) Math.ceil(service.countUsers() * 1.0 / USERS_PER_PAGE);
        List<User> users = service.findCustomers(page, USERS_PER_PAGE)
                .orElse(null);
        putAttrInRequest(request, RequestAttribute.USERS_LIST, users);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadBlackList(final HttpServletRequest request, final int page) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<BlackListNode> blackList = service.readBlackList(page, USERS_PER_PAGE)
                .orElse(null);
        int numOfPages = (int) Math.ceil(service.countUsers() * 1.0 / USERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.BLACK_LIST, "blList");
        putAttrInRequest(request, RequestAttribute.USERS_LIST, blackList);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void updateOrdersStatus() {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findActiveOrders().orElseGet(ArrayList::new);
        orders.stream()
                .filter(order -> order.getReturnDate().isBefore(LocalDateTime.now()))
                .forEach(order -> service.updateOrderStatus(order.getId(), OrderStatus.EXPIRED));
    }
}
