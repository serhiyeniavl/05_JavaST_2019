package by.training.info_system.command;

import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    ServiceFactory factory;


    void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract JspPage execute(final HttpServletRequest request, final HttpServletResponse response);
}
