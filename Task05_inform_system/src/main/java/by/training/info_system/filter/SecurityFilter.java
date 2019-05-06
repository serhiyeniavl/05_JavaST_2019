package by.training.info_system.filter;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.entity.User;
import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.ConfigurationManager;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String requestedPage = (String) httpRequest.getAttribute("requestedPage");
        JspPage jspPage = PageFactory.defineAndGet(
                PageEnum.valueOf(requestedPage.toUpperCase()));

        User user;
        HttpSession session = httpRequest.getSession(false);
        if (session == null
                || session.getAttribute("user") == null) {
            user = User.builder()
                    .role(Role.UNAUTHORIZED_USER)
                    .build();
        } else {
            user = (User) session.getAttribute("user");
        }

        boolean isAllowed = jspPage.getAllowedRoles()
                .contains(user.getRole());
        if (isAllowed) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpRequest.setAttribute(RequestAttribute.INFO.getValue(),
                    "This page requires more secure.");
            httpRequest.getRequestDispatcher(
                    ConfigurationManager.getInstance().getPagePath("signin"))
                    .forward(servletRequest, servletResponse);
        }
    }
}
