package by.training.info_system.command;

import by.training.info_system.resource.page.JspPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManager {
    JspPage execute(Command command, HttpServletRequest request, HttpServletResponse response);

    void close();
}
