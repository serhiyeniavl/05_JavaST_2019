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
    private static final int URL_TIMEOUT = 7;

    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String requestedPage = (String) request.getAttribute("requestedPage");
        JspPage page = PageFactory.defineAndGet(PageEnum.valueOf(requestedPage.toUpperCase()));

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
            loadOrderStatuses(request);
            int pageNum = 1;
            if (request.getParameter(RequestParameter.PAGE.getValue()) != null) {
                pageNum = Integer.parseInt(request.getParameter(RequestParameter.PAGE.getValue()));
                putAttrInRequest(request, RequestAttribute.CURRENT_PAGE, pageNum);
            } else {
                appendRequestParameterWithoutEncoding(page, RequestParameter.PAGE,
                        String.valueOf(pageNum));
                page.setRedirect(true);
                return page;
            }
            if (page.getUri().equals(PageEnum.ORDERS.getUri())) {
                loadOrders(request, pageNum);
                if (checkRequestMessageAttrs(request)
                        && checkOrderIdMsg(request)) {
                    return processOrderIdAttr(request, processMessageAttrs(request, page));
                }
            } else {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("user");
                loadUserOrders(request, user.getId(), pageNum);
                if (checkRequestMessageAttrs(request)
                        && checkOrderIdMsg(request)) {
                    return processOrderIdAttr(request, processMessageAttrs(request, page));
                }
            }
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
                loadManagers(request);
            } else if (request.getParameter(RequestParameter.SHOW.getValue())
                    .equals(RequestAttribute.CUSTOMERS.getValue())) {
                loadCustomers(request);
            } else if (request.getParameter(RequestParameter.SHOW.getValue())
                    .equals(RequestAttribute.BLACK_LIST.getValue())) {
                loadBlackList(request);
            }
            return page;
        } else if (request.getParameter(RequestParameter.ID.getValue()) != null) {
            loadUserById(request, Long.valueOf(request.getParameter("id")));
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

    private void loadCars(HttpServletRequest request) {
        CarService service = factory.getService(CarService.class).orElseThrow();
        List<Car> cars = service.loadCars().orElseGet(ArrayList::new);
        putAttrInRequest(request, RequestAttribute.CARS, cars);
    }

    private void loadOrders(HttpServletRequest request, int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findAllOrders(pageNum, ORDERS_PER_PAGE).orElseGet(ArrayList::new);
        int numOfPages = (int) Math.ceil(service.countOrders() * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadProfileInfo(HttpServletRequest request, long userId) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        User user = service.findById(userId).orElse(User.builder().build());
        putAttrInRequest(request, RequestAttribute.PROFILE, user);
    }

    private void loadOrderStatuses(HttpServletRequest request) {
        putAttrInRequest(request, RequestAttribute.ORDER_STATUS, OrderStatus.values());
    }

    private void loadUserOrders(final HttpServletRequest request, final long id, final int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findUserOrders(id, page, ORDERS_PER_PAGE)
                .orElseGet(ArrayList::new);
        int numOfPages = (int) Math.ceil(service.countOrders(id) * 1.0 / ORDERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    private void loadUsers(final HttpServletRequest request, final int page) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<User> users = service.findAll(page, USERS_PER_PAGE).orElse(null);
        int numOfPages = (int) Math.ceil(service.countUsers() * 1.0 / USERS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.USERS_LIST, users);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
        putAttrInRequest(request, RequestAttribute.SHOW,
                RequestAttribute.ALL_USERS.getValue());
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

    private void loadManagers(final HttpServletRequest request) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<User> users = service.findManagers().orElseGet(ArrayList::new);
        putAttrInRequest(request, RequestAttribute.USERS_LIST, users);
    }

    private void loadCustomers(final HttpServletRequest request) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<User> users = service.findCustomers().orElseGet(ArrayList::new);
        putAttrInRequest(request, RequestAttribute.USERS_LIST, users);
    }

    private void loadBlackList(final HttpServletRequest request) {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<BlackListNode> blackList = service.readBlackList().orElseGet(ArrayList::new);
        putAttrInRequest(request, RequestAttribute.BLACK_LIST, "blList");
        putAttrInRequest(request, RequestAttribute.USERS_LIST, blackList);
    }
}
