package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.Passport;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.service.UserService;
import by.training.info_system.util.PasswordHasher;
import by.training.info_system.validator.UserValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class SignUpCommand extends Command {

    @Override
    public JspPage execute(final HttpServletRequest request, final HttpServletResponse response) {
        JspPage page = PageFactory.defineAndGet(PageEnum.SIGNUP);

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String fName = request.getParameter("fname").trim();
        String lName = request.getParameter("lname").trim();
        String address = request.getParameter("address").trim();
        String passportNumber = request.getParameter("ps_number").trim();
        String passportIdNumber = request.getParameter("ps_id").trim();
        String passportIssueDate = request.getParameter("ps_issue").trim();
        String passportEndDate = request.getParameter("ps_end").trim();

        User user;
        Passport userPassport;
        UserData userData;
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            userPassport = Passport.builder()
                    .serie(passportNumber.substring(0, 2))
                    .number(Integer.valueOf(passportNumber.substring(2)))
                    .idNumber(passportIdNumber)
                    .issueDate(LocalDate.parse(passportIssueDate, timeFormatter))
                    .endDate(LocalDate.parse(passportEndDate, timeFormatter))
                    .build();

            userData = UserData.builder()
                    .fName(fName)
                    .lName(lName)
                    .passport(userPassport)
                    .address(address)
                    .build();

            user = User.builder()
                    .login(email)
                    .password(PasswordHasher.hashPass(password))
                    .role(Role.USER)
                    .userData(userData)
                    .build();
        } catch (Exception e) {
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.INCORRECT_SIGNUP_FORM);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.toString());
            return page;
        }

        if (!validate(new UserValidator(), user)) {
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.INCORRECT_SIGNUP_FORM);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.toString());
            return page;
        }

        UserService service = factory.getService(UserService.class).orElseThrow();
        if (service.isInBlackList(user)) {
            page = PageFactory.defineAndGet(PageEnum.SIGNIN);
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.BANNED_ACCOUNT_EXIST);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.BLACK_LIST.toString());
            return page;
        }
        if (service.isExist(userPassport.getNumber(), userPassport.getIdNumber())) {
            page = PageFactory.defineAndGet(PageEnum.SIGNIN);
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.USER_WITH_PASSPORT_EXIST);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.INCORRECT_DATA.toString());
            return page;
        }
        Integer isCreated = service.registerNewUser(user);
        if (isCreated != 0) {
            page = PageFactory.defineAndGet(PageEnum.SIGNIN);
            appendRequestParameter(page, RequestParameter.TIME,
                    LocalDateTime.now().toString());
            appendRequestParameter(page, RequestParameter.MESSAGE,
                    RequestMessage.SUCCESSFUL_SIGNUP);
            appendRequestParameter(page, RequestParameter.ATTRIBUTE,
                    RequestAttribute.SIGNED_UP.toString());
        }
        return page;
    }
}
