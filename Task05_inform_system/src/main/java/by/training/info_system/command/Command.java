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

public abstract class Command {

    protected ServiceFactory factory;

    static final int ORDERS_PER_PAGE = 10;
    static final int USERS_PER_PAGE = 10;


    void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract JspPage execute(HttpServletRequest request,
                                    HttpServletResponse response);

    void putAttrInRequest(final HttpServletRequest request,
                          final RequestAttribute attribute,
                          final Object o) {
        request.setAttribute(attribute.getValue(), o);
    }

    protected void appendRequestParameter(final JspPage page,
                                          final RequestParameter parameter,
                                          RequestMessage message) {
        page.appendRequestParameter(parameter.getValue() + "="
                + Encoder.encodeString(message.getValue()));
    }

    protected void appendRequestParameter(final JspPage page,
                                          final RequestParameter parameter,
                                          final String message) {
        page.appendRequestParameter(parameter.getValue() + "="
                + Encoder.encodeString(message));
    }

    void appendRequestParameterWithoutEncoding(final JspPage page,
                                               final RequestParameter parameter,
                                               final String message) {
        page.appendRequestParameter(parameter.getValue() + "=" + message);
    }

    void updateBlackList() {
        UserService service = factory.getService(UserService.class)
                .orElseThrow();
        List<BlackListNode> blackList = service.readBlackList()
                .orElseGet(ArrayList::new);
        blackList.stream()
                .filter(node -> node.getUnlockDate().isBefore(LocalDate.now()))
                .forEach(node -> service.deleteFromBlackList(node.getUser().getId()));
    }

    protected void appendTimeParam(final JspPage page) {
        appendRequestParameter(page, RequestParameter.TIME,
                LocalDateTime.now().toString());
    }

    protected String findCurrentParameters(final HttpServletRequest request) {
        return request.getHeader("referer")
                .substring(request.getRequestURL().length());
    }

    protected boolean validate(final Validator validator,
                               final Object object) {
        return validator.validate(object);
    }
}
