package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.ServiceFactory;
import by.training.info_system.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    ServiceFactory factory;


    void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract JspPage execute(HttpServletRequest request, HttpServletResponse response);

    void putAttrInRequest(HttpServletRequest request, RequestAttribute attribute,
                          Object o) {
        request.setAttribute(attribute.getValue(), o);
    }

    boolean validate(Validator validator, Object object) {
        return validator.validate(object);
    }
}
