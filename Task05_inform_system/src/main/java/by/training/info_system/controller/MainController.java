package by.training.info_system.controller;

import by.training.info_system.command.ActionCommand;
import by.training.info_system.factory.ActionCommandFactory;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/freeride")
public class MainController extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response) {
        processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest req,
                                final HttpServletResponse resp) {
        String page;

        ActionCommand command = ActionCommandFactory.defineCommand(req);

        page = command.execute(req);
        try {
            if (page != null) {
                getServletContext().getRequestDispatcher(page).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + page);
            }
        } catch (ServletException e) {
            log.error("Servlet exception when forward req and resp", e);
        } catch (IOException e) {
            log.error("Servlet IO exception", e);
        }
    }
}
