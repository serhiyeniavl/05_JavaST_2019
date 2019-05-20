package by.training.info_system.command.admin;

import by.training.info_system.command.Command;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.USERS);
        String currentParams = findCurrentParameters(request);
        page.appendRequestParameter(currentParams.substring(1));

        UserService service = factory.getService(UserService.class)
                .orElseThrow();
        long userId = Long.parseLong(request.getParameter("user_id"));
        service.delete(userId);
        return page;
    }
}
