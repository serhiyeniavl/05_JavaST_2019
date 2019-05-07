package by.training.info_system.command;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.entity.User;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RentCarCommand extends Command {
    @Override
    public JspPage execute(final HttpServletRequest request,
                           final HttpServletResponse response) {

        JspPage page = PageFactory.defineAndGet(PageEnum.CARS);
        System.out.println(request.getParameter("rent_butt"));

        HttpSession session = request.getSession(false);
        if (session == null
                || session.getAttribute("user") == null) {
            page = PageFactory.defineAndGet(PageEnum.SIGNIN);
            putAttrInRequest(request, RequestAttribute.INFO,
                    "To rent a car you need to sign in.");
            return page;
        }

        User user = (User) session.getAttribute("user");

        return page;
    }
}
