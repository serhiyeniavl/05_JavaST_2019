package by.training.info_system.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class URICommandFilter implements Filter {
    private static final List<String> URI_LIST = new ArrayList<>();

    static {
        URI_LIST.add("/");
        URI_LIST.add("/home");
        URI_LIST.add("/cars");
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String requestedURI = request.getRequestURI();

            int end = requestedURI.lastIndexOf('.');
            String page;
            if (end > 0) {
                page = requestedURI.substring(request.getContextPath().length(), end);
            } else {
                page = requestedURI.substring(request.getContextPath().length());
            }
            if (!URI_LIST.contains(page)) {
                redirectToErrorPage(servletRequest, servletResponse);
                return;
            }
            request.setAttribute("page", page);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.error("Cannot use HTTP filter");
            redirectToErrorPage(servletRequest, servletResponse);
        }
    }

    private void redirectToErrorPage(final ServletRequest request,
                                     final ServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
}
