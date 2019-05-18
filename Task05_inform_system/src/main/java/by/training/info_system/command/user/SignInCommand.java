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
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

public class SignInCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.SIGNIN);

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        UserService service = factory.getService(UserService.class).orElseThrow();
        Optional<User> user = service.findByEmail(email);
        if (user.isPresent()
                && PasswordHasher.checkPass(pass, user.get().getPassword())) {
            if (service.isInBlackList(user.get())) {
                appendTimeParam(page);
                appendRequestParameter(page, RequestParameter.MESSAGE,
                        RequestMessage.BANNED_ACCOUNT);
                appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                        RequestAttribute.BLACK_LIST.toString());
                return page;
            }
            HttpSession session = request.getSession(false);
            User sessionUser = User.builder()
                    .email(user.get().getEmail())
                    .role(user.get().getRole())
                    .userData(user.get().getUserData())
                    .build();
            sessionUser.setId(user.get().getId());
            session.setAttribute("user", sessionUser);

            page = PageFactory.defineAndGet(PageEnum.HOME);
        } else {
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.INCORRECT_SIGNIN);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.toString());
        }
        return page;
    }
}
