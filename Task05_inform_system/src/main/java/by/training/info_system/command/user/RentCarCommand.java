package by.training.info_system.command.user;

import by.training.info_system.command.Command;
import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.User;
import by.training.info_system.entity.status.OrderStatus;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.CarService;
import by.training.info_system.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class RentCarCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {

        JspPage page = PageFactory.defineAndGet(PageEnum.CARS);

        HttpSession session = request.getSession(false);
        if (session == null
                || session.getAttribute("user") == null) {
            page = PageFactory.defineAndGet(PageEnum.SIGNIN);
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.SIGNIN_TO_RENT);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INFO.toString());
            return page;
        }

        CarService carService = factory.getService(CarService.class).orElseThrow();
        Car car = carService.findById(
                Long.valueOf(request.getParameter("rent_butt"))).orElseThrow();
        User user = (User) session.getAttribute("user");
        OrderService orderService = factory.getService(OrderService.class).orElseThrow();
        if (orderService.isActiveOrder(user.getId())) {
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.ACTIVE_ORDER);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INFO.toString());
            return page;
        }

        Order order = Order.builder()
                .status(OrderStatus.NOT_CONFIRMED)
                .car(car)
                .user(user)
                .build();

        int newOrderId = orderService.createNewOrder(order);
        if (newOrderId != 0) {
            order.setId((long) newOrderId);
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.ORDER_WAIT);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INFO.toString());
        } else {
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.ORDER_GOES_WRONG);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INFO.toString());
        }
        return page;
    }
}
