package by.training.info_system.factory;

import by.training.info_system.command.ActionCommand;
import by.training.info_system.command.client.CommandEnum;

import javax.servlet.http.HttpServletRequest;


public class ActionCommandFactory {

    private ActionCommandFactory() {
    }

    public static ActionCommand defineCommand(final HttpServletRequest request) {
        ActionCommand current = null;

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
