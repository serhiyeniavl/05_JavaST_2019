package by.training.info_system.command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
