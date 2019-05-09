package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.User;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.CarService;
import by.training.info_system.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RentCarCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {

        JspPage page = PageFactory.defineAndGet(PageEnum.CARS);

        HttpSession session = request.getSession(false);
        if (session == null
                || session.getAttribute("user") == null) {
            page = PageFactory.defineAndGet(PageEnum.SIGNIN);
            putAttrInRequest(request, RequestAttribute.INFO,
                    "To rent a car you need to sign in.");
            return page;
        }

        CarService carService = factory.getService(CarService.class).orElseThrow();

        Car car = carService.findById(
                Long.valueOf(request.getParameter("rent_butt"))).orElseThrow();
        User user = (User) session.getAttribute("user");
        Order order = Order.builder()
                .car(car)
                .user(user)
                .build();

        OrderService orderService = factory.getService(OrderService.class).orElseThrow();
        if (orderService.createNewOrder(order)) {
            putAttrInRequest(request, RequestAttribute.INFO, "We've got you order." +
                    " Wait a confirmation from your managers. You can check order" +
                    " status in your orders.");
        } else {
            putAttrInRequest(request, RequestAttribute.INCORRECT_DATA,
                    "Sorry, something goes wrong. Try later.");
        }

        loadCars(request);
        return page;
    }
}
