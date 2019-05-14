package by.training.info_system.filter;

import by.training.info_system.resource.ConfigurationManager;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class URIDefinerFilter implements Filter {
    private static final List<String> URI_LIST = new ArrayList<>();

    static {
        URI_LIST.add("/");
        URI_LIST.add("/home");
        URI_LIST.add("/cars");
        URI_LIST.add("/signin");
        URI_LIST.add("/signup");
        URI_LIST.add("/contact");
        URI_LIST.add("/orders");
        URI_LIST.add("/profile");
        URI_LIST.add("/my_orders");
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String requestedURI = request.getRequestURI();

            String page = requestedURI.substring(request.getContextPath().length());

            if (!URI_LIST.contains(page)) {
                redirectToErrorPage(servletRequest, servletResponse);
                return;
            }
            String requestedPage = page.substring(1);
            request.setAttribute("requestedPage", requestedPage);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.error("Cannot use HTTP filter");
            redirectToErrorPage(servletRequest, servletResponse);
        }
    }

    private void redirectToErrorPage(final ServletRequest request,
                                     final ServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher(
                ConfigurationManager.getInstance().getPagePath("error"))
                .forward(request, response);
    }
}
