package by.training.info_system.command;

import by.training.info_system.entity.Passport;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.UserService;
import by.training.info_system.util.PasswordHasher;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
public class SignUpCommand extends Command {

    @Override
    public JspPage execute(final HttpServletRequest request, final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.SIGNUP);
        page.setRedirect(true);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String address = request.getParameter("address");
        String passportNumber = request.getParameter("ps_number");
        String passportIdNumber = request.getParameter("ps_id");
        String passportIssueDate = request.getParameter("ps_issue");
        String passportEndDate = request.getParameter("ps_end");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Passport userPassport = Passport.builder()
                .serie(passportNumber.substring(0, 2))
                .number(Integer.valueOf(passportNumber.substring(2)))
                .idNumber(passportIdNumber)
                .issueDate(LocalDate.parse(passportIssueDate, timeFormatter))
                .endDate(LocalDate.parse(passportEndDate, timeFormatter))
                .build();

        UserData userData = UserData.builder()
                .fName(fName)
                .lName(lName)
                .passport(userPassport)
                .address(address)
                .build();

        User user = User.builder()
                .login(email)
                .password(PasswordHasher.hashPass(password))
                .role(Role.USER)
                .userData(userData)
                .build();


        UserService service = factory.getService(UserService.class).orElseThrow();
        boolean isCreated = service.registerNewUser(user);
        if (isCreated) {
            User sessionUser = User.builder()
                    .login(user.getLogin())
                    .role(user.getRole())
                    .userData(UserData.builder().fName(userData.getFName()).build())
                    .build();
            HttpSession session = request.getSession(false);
            session.setAttribute("user", sessionUser);

            //TODO: make request to the db and count the discount
            session.setAttribute("discount", "0");

            page = PageFactory.defineAndGet(PageEnum.HOME);
        }
        return page;
    }
}
