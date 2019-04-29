package by.training.info_system.command;

import by.training.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class Command {
    ServiceFactory factory;


    void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract String execute(final HttpServletRequest request);
}
