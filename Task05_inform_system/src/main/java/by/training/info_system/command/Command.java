package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.ServiceFactory;
import by.training.info_system.util.Encoder;
import by.training.info_system.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public abstract class Command {

    protected ServiceFactory factory;

    static final int RECORDS_PER_PAGE = 3;


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

    protected void appendTimeParam(JspPage page) {
        appendRequestParameter(page, RequestParameter.TIME,
                LocalDateTime.now().toString());
    }

    protected String findCurrentPage(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return String.valueOf(referer.charAt(referer.length() - 1));
    }

    protected boolean validate(Validator validator, Object object) {
        return validator.validate(object);
    }
}
