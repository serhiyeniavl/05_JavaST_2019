package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.entity.User;
import by.training.info_system.resource.ConfigurationManager;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.UserService;
import by.training.info_system.util.PasswordHasher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request, final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.SIGNIN);

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        UserService service = factory.getService(UserService.class).orElseThrow();
        Optional<User> user = service.findByLogin(email);
        if (user.isPresent()
                && PasswordHasher.checkPass(pass, user.get().getPassword())) {
            if (service.isInBlackList(user.get())) {
                putAttrInRequest(request, RequestAttribute.BLACK_LIST,
                        "This account have been banned.");
                return page;
            }
            HttpSession session = request.getSession(false);
            User sessionUser = User.builder()
                    .login(user.get().getLogin())
                    .role(user.get().getRole())
                    .userData(user.get().getUserData())
                    .build();
            sessionUser.setId(user.get().getId());
            session.setAttribute("user", sessionUser);

            //TODO: make request to the db and count the discount
            session.setAttribute("discount", "0");

            page = PageFactory.defineAndGet(PageEnum.HOME);
            page.setRedirect(true);
        } else {
            putAttrInRequest(request, RequestAttribute.INCORRECT_DATA,
                    "Incorrect email or password");
        }
        return page;
    }
}
