package by.training.info_system.command.admin;

import by.training.info_system.command.Command;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserFromBlackListCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.USERS);
        page.appendRequestParameter(findCurrentParameters(request).substring(1));
        long userId = Long.parseLong(request.getParameter("user_id"));

        UserService service = factory.getService(UserService.class).orElseThrow();
        service.deleteFromBlackList(userId);
        return page;
    }
}
