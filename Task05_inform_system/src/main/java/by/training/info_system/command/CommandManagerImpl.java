package by.training.info_system.command;

import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    CommandManagerImpl(final ServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public JspPage execute(final Command command, final HttpServletRequest request, final HttpServletResponse response) {
        command.setFactory(factory);
        return command.execute(request, response);
    }

    @Override
    public void close() {
        factory.close();
    }
}
