package by.training.info_system.command;

import by.training.info_system.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    CommandManagerImpl(final ServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public String execute(final Command command, final HttpServletRequest request) {
        command.setFactory(factory);
        return command.execute(request);
    }

    @Override
    public void close() {
        factory.close();
    }
}
