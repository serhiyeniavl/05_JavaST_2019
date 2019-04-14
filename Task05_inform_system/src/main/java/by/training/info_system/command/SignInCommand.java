package by.training.info_system.command;

import by.training.info_system.dao.UserDao;
import by.training.info_system.dao.UserDaoImpl;
import by.training.info_system.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements ActionCommand {
    @Override
    public String execute(final HttpServletRequest request) {
        String page;
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        UserDao userDao = new UserDaoImpl();
        if (!userDao.findUserToSignIn(email, pass).isEmpty()) {
            //page = ConfigurationManager.getProperty("path.page.index");
            page = ConfigurationManager.indexPage;
        } else {
//            page = ConfigurationManager.getProperty("path.page.signin");
            page = ConfigurationManager.signinPage;
        }
        return page;
    }
}
