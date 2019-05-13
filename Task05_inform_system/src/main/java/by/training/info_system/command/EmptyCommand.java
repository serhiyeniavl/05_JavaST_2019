package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.User;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.util.Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmptyCommand extends Command {
    private static final int URL_TIMEOUT = 7;

    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String requestedPage = (String) request.getAttribute("requestedPage");
        JspPage page = PageFactory.defineAndGet(PageEnum.valueOf(requestedPage.toUpperCase()));

        if ((page.getUri().equals(PageEnum.SIGNIN.getUri())
                || page.getUri().equals(PageEnum.SIGNUP.getUri()))) {
            if (checkRequestMessageAttrs(request)) {
                return processMessageAttrs(request, page);
            } else if (checkSecureAttr(request)) {
                return processSecureAttr(request, page);
            }
        }

        if (page.getUri().equals(PageEnum.CARS.getUri())) {
            loadCars(request);
            if (checkRequestMessageAttrs(request)) {
                return processMessageAttrs(request, page);
            }
        }

        if (isAuthorizedUserTrySignAgain(request, page)) {
            page = PageFactory.defineAndGet(PageEnum.HOME);
            page.setRedirect(true);
            return page;
        }

        if (page.getUri().equals(PageEnum.ORDERS.getUri())
                || page.getUri().equals(PageEnum.MY_ORDERS.getUri())) {
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
}
