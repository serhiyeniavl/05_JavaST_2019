package by.training.info_system.command;

import by.training.info_system.service.ServiceFactory;

public final class CommandManagerFactory {

    private CommandManagerFactory() {
    }

    public static CommandManager getManager(final ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
