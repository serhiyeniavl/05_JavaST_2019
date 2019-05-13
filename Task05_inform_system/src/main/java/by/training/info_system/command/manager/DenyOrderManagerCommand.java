package by.training.info_system.command.manager;

import by.training.info_system.command.Command;
import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.status.OrderStatus;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class DenyOrderManagerCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.ORDERS);

        Long orderId = Long.valueOf(request.getParameter("deny"));
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        boolean isUpdated = service.updateOrderStatus(orderId, OrderStatus.DENIED);
        appendRequestParameter(page, RequestParameter.TIME,
                LocalDateTime.now().toString());
        appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                RequestAttribute.INFO.toString());
        String referer = request.getHeader("referer");
        String pageNum = String.valueOf(referer.charAt(referer.length() - 1));
        appendRequestParameterWithoutEncoding(page, RequestParameter.PAGE, pageNum);
        if (isUpdated) {
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.DENIED_ORDER);
            appendRequestParameter(page, RequestParameter.ORDER_ID,
                    orderId.toString());
        } else {
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.UPDATED_ORDER_STATUS_WRONG);
        }
        return page;
    }
}
