package by.training.info_system.command.user;

import by.training.info_system.command.Command;
import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.status.OrderStatus;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class AcceptOrderCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.MY_ORDERS);

        Long orderId = Long.valueOf(request.getParameter("accept"));
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        Order order = service.findOrderById(orderId).orElseThrow();
        order.setIssueDate(LocalDateTime.now());
        order.setReturnDate(LocalDateTime.now().plusDays(1L));
        order.setStatus(OrderStatus.ACTIVE);
        boolean isAccepted = service.updateOrder(order);
        appendTimeParam(page);
        String pageNum = findCurrentPage(request);
        appendRequestParameterWithoutEncoding(page, RequestParameter.PAGE, pageNum);
        if (isAccepted) {
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INFO.toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.USER_TOOK_A_CAR);
            appendRequestParameter(page, RequestParameter.ORDER_ID,
                    orderId.toString());
        } else {
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.TOOK_A_CAR_GOES_WRONG);
        }
        return page;
    }
}
