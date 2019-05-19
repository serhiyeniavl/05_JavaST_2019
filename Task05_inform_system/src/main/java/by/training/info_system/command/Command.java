package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.BlackListNode;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.ServiceFactory;
import by.training.info_system.service.UserService;
import by.training.info_system.util.Encoder;
import by.training.info_system.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Command {

    protected ServiceFactory factory;

    static final int ORDERS_PER_PAGE = 10;
    static final int USERS_PER_PAGE = 10;


    void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract JspPage execute(HttpServletRequest request,
                                    HttpServletResponse response);

    void putAttrInRequest(HttpServletRequest request, RequestAttribute attribute,
                          Object o) {
        request.setAttribute(attribute.getValue(), o);
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

    protected void updateBlackList() {
        UserService service = factory.getService(UserService.class).orElseThrow();
        List<BlackListNode> blackList = service.readBlackList().orElseGet(ArrayList::new);
        blackList.stream()
                .filter(node -> node.getUnlockDate().isBefore(LocalDate.now()))
                .forEach(node -> service.deleteFromBlackList(node.getUser().getId()));
    }

    protected void appendTimeParam(JspPage page) {
        appendRequestParameter(page, RequestParameter.TIME,
                LocalDateTime.now().toString());
    }

    protected String findCurrentParameters(HttpServletRequest request) {
        return request.getHeader("referer").substring(request.getRequestURL().length());
    }

    protected boolean validate(Validator validator, Object object) {
        return validator.validate(object);
    }
}
