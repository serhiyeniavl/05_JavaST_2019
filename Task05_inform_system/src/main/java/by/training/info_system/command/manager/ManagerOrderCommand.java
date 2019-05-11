package by.training.info_system.command.manager;

import by.training.info_system.command.Command;
import by.training.info_system.resource.page.JspPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerOrderCommand extends Command {
    private static final List<String> MANAGER_COMMANDS
            = Collections.synchronizedList(new ArrayList<>());

    static {
        MANAGER_COMMANDS.add("deny");
        MANAGER_COMMANDS.add("confirm");
    }

    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String action = MANAGER_COMMANDS.stream()
                .filter(command -> request.getParameter(command) != null)
                .findFirst()
                .orElse("empty");
        Command managerCommand = ManagerCommandEnum.valueOf(action.toUpperCase())
                .getManagerCommand();
        return managerCommand.execute(request, response);
    }
}
