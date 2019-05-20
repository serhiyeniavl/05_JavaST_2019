package by.training.info_system.command.user;

import by.training.info_system.command.Command;
import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.User;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.UserService;
import by.training.info_system.util.PasswordHasher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.PROFILE);

        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password1");
        String newPasswordRepeat = request.getParameter("new_password2");

        appendTimeParam(page);

        if (!newPassword.equals(newPasswordRepeat)) {
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.getValue());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.PASSWORDS_INCORRECT);
            return page;
        }

        User user = (User) request.getSession(false).getAttribute("user");
        UserService service = factory.getService(UserService.class)
                .orElseThrow();
        if (isOldPassCorrect(user, service, oldPassword)) {
            if (service.updatePassword(user.getId(),
                    PasswordHasher.hashPass(newPassword))) {
                appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                        RequestAttribute.INFO.getValue());
                appendRequestParameter(page, RequestParameter.MESSAGE,
                        RequestMessage.PASSWORD_CHANGED);
            } else {
                appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                        RequestAttribute.INCORRECT_DATA.getValue());
                appendRequestParameter(page, RequestParameter.MESSAGE,
                        RequestMessage.PASSWORD_CHANGE_GOES_WRONG);
            }
        }
        return page;
    }

    private boolean isOldPassCorrect(final User user,
                                     final UserService service,
                                     final String password) {
        User u = service.findById(user.getId()).orElseThrow();
        return PasswordHasher.checkPass(password, u.getPassword());
    }
}
