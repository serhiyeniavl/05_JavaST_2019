package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.Order;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.CarService;
import by.training.info_system.service.OrderService;
import by.training.info_system.service.ServiceFactory;
import by.training.info_system.util.Encoder;
import by.training.info_system.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    protected ServiceFactory factory;

    private static final int RECORDS_PER_PAGE = 3;


    void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract JspPage execute(HttpServletRequest request, HttpServletResponse response);

    void putAttrInRequest(HttpServletRequest request, RequestAttribute attribute,
                          Object o) {
        request.setAttribute(attribute.getValue(), o);
    }

    void loadCars(HttpServletRequest request) {
        CarService service = factory.getService(CarService.class).orElseThrow();
        List<Car> cars = service.loadCars().orElseGet(ArrayList::new);
        putAttrInRequest(request, RequestAttribute.CARS, cars);
    }

    void loadUserOrders(HttpServletRequest request, long id, int page) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findUserOrders(id, page, RECORDS_PER_PAGE).orElseGet(ArrayList::new);
        int numOfPages = (int) Math.ceil(service.countOrders(id) * 1.0 / RECORDS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    void loadOrders(HttpServletRequest request, int pageNum) {
        OrderService service = factory.getService(OrderService.class).orElseThrow();
        List<Order> orders = service.findAllOrders(pageNum, RECORDS_PER_PAGE).orElseGet(ArrayList::new);
        int numOfPages = (int) Math.ceil(service.countOrders() * 1.0 / RECORDS_PER_PAGE);
        putAttrInRequest(request, RequestAttribute.ORDERS, orders);
        putAttrInRequest(request, RequestAttribute.NUM_OF_PAGES, numOfPages);
    }

    protected void appendRequestParameter(JspPage page, RequestParameter parameter,
                                          RequestMessage message) {
        page.appendRequestParameter(parameter.getValue() + "="
                + Encoder.encodeString(message.getValue()));
    }

    protected void appendRequestParameter(JspPage page, RequestParameter parameter,
                                          String message) {
        page.appendRequestParameter(parameter.getValue() + "="
                + Encoder.encodeString(message));
    }

    protected void appendRequestParameterWithoutEncoding(JspPage page,
                                                         RequestParameter parameter,
                                                         String message) {
        page.appendRequestParameter(parameter.getValue() + "=" + message);
    }

    protected boolean validate(Validator validator, Object object) {
        return validator.validate(object);
    }
}
