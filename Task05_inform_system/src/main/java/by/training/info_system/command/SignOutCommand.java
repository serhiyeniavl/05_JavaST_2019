package by.training.info_system.command;

import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOutCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request, final HttpServletResponse response) {
        request.getSession(false).invalidate();
        return PageFactory.defineAndGet(PageEnum.SIGNIN);
    }
}
