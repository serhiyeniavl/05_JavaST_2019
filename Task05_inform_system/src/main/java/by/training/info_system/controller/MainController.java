package by.training.info_system.controller;

import by.training.info_system.command.ActionCommandFactory;
import by.training.info_system.command.Command;
import by.training.info_system.command.CommandManager;
import by.training.info_system.command.CommandManagerFactory;
import by.training.info_system.dao.connection_pool.ConnectionPool;
import by.training.info_system.dao.impl.DaoManagerImpl;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.service.impl.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class MainController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ConnectionPool.getInstance();
    }

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
        Command command = ActionCommandFactory.defineCommand(req);
        CommandManager commandManager = CommandManagerFactory.getManager(new ServiceFactoryImpl(new DaoManagerImpl()));

        JspPage redirectPage = commandManager.execute(command, req, resp);
        commandManager.close();

        try {
            if (redirectPage.isRedirect()) {
                resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/" + redirectPage.getUri()));
            } else {
                getServletContext().getRequestDispatcher(redirectPage.getJspPagePath()).forward(req, resp);
            }
        } catch (ServletException e) {
            log.error("Servlet exception when forward req and resp", e);
        } catch (IOException e) {
            log.error("Servlet IO exception", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().finalizePool();
    }
}
