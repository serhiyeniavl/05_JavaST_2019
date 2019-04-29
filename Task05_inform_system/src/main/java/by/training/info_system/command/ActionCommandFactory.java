package by.training.info_system.command;

import by.training.info_system.command.Command;
import by.training.info_system.command.EmptyCommand;
import by.training.info_system.command.client.CommandEnum;

import javax.servlet.http.HttpServletRequest;


public final class ActionCommandFactory {

    private ActionCommandFactory() {
    }

    public static Command defineCommand(final HttpServletRequest request) {
        Command current = new EmptyCommand();

        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            current = CommandEnum.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
        }
        return current;
    }
}
