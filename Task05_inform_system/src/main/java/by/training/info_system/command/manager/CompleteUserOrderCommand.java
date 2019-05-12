package by.training.info_system.command.manager;

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
import java.time.temporal.ChronoUnit;

public class CompleteUserOrderCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.ORDERS);

        Long orderId = Long.valueOf(request.getParameter("complete"));
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        boolean isUpdatedStatus = service.updateOrderStatus(orderId, OrderStatus.COMPLETED);
        Order order = service.findOrderById(orderId).orElseThrow();
        order.setRealReturnDate(LocalDateTime.now());
        order.setFinalPrice(calculatePrice(order));
        boolean isUpdatedOrder = service.updateOrder(order);
        appendRequestParameter(page, RequestParameter.TIME,
                LocalDateTime.now().toString());
        appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                RequestAttribute.INFO.toString());
        if (isUpdatedStatus && isUpdatedOrder) {
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.COMPLETED_USER_ORDER);
            appendRequestParameter(page, RequestParameter.ORDER_ID,
                    orderId.toString());

        } else {
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.UPDATED_ORDER_STATUS_WRONG);
        }

        return page;
    }

    private Long calculatePrice(final Order order) {
        LocalDateTime tempTime = LocalDateTime.from(order.getIssueDate());
        long hours = tempTime.until(order.getRealReturnDate(), ChronoUnit.HOURS);
        return order.getCar().getRentPrice() * hours;
    }
}
