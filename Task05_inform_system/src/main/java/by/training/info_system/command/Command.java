package by.training.info_system.command;

import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    ServiceFactory factory;


    void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract JspPage execute(HttpServletRequest request, HttpServletResponse response);

    void putAttrInRequest(HttpServletRequest request, String s, Object o) {
        request.setAttribute(s, o);
    }
}
