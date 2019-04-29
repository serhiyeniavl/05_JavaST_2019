package by.training.info_system.command;

import javax.servlet.http.HttpServletRequest;

public interface CommandManager {
    String execute(final Command command, final HttpServletRequest request);

    void close();
}
