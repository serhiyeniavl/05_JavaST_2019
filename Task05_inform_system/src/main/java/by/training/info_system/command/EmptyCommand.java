package by.training.info_system.command;

import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request, final HttpServletResponse response) {
        String requestedPage = (String) request.getAttribute("requestedPage");
        JspPage page = PageFactory.defineAndGet(PageEnum.valueOf(requestedPage.toUpperCase()));

        if (isAuthorizedUserTrySignAgain(request, page)) {
            page = PageFactory.defineAndGet(PageEnum.HOME);
            page.setRedirect(true);
        }
        return page;
    }

    private boolean isAuthorizedUserTrySignAgain(final HttpServletRequest request, JspPage page) {
        return (request.getSession(false) != null
                && request.getSession(false).getAttribute("user") != null
                && (page.getUri().equals(PageEnum.SIGNIN.getUri())
                || page.getUri().equals((PageEnum.SIGNUP.getUri()))));
    }
}
