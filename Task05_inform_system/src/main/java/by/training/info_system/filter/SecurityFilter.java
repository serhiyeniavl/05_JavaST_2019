package by.training.info_system.filter;

import by.training.info_system.command.client.RequestAttribute;
import by.training.info_system.command.client.RequestParameter;
import by.training.info_system.entity.User;
import by.training.info_system.entity.role.Role;
import by.training.info_system.resource.message.RequestMessage;
import by.training.info_system.resource.page.JspPage;
import by.training.info_system.resource.page.PageEnum;
import by.training.info_system.resource.page.PageFactory;
import by.training.info_system.util.Encoder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class SecurityFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

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
            jspPage = PageFactory.defineAndGet(PageEnum.SIGNIN);
            jspPage.appendRequestParameter(RequestParameter.SECURITY.getValue()
                    + "=" + Encoder.encodeString(RequestMessage.MORE_SECURE.getValue()));
            jspPage.appendRequestParameter(RequestParameter.TIME.getValue()
            + "=" + Encoder.encodeString(LocalDateTime.now().toString()));
            jspPage.appendRequestParameter(RequestParameter.ATTRIBUTE.getValue()
            + "=" + Encoder.encodeString(RequestAttribute.INFO.getValue()));
            httpResponse.sendRedirect(httpResponse.encodeRedirectURL(httpRequest.getContextPath()
                    + "/" + jspPage.getUri() + jspPage.getRequestParameters()));
        }
    }
}
