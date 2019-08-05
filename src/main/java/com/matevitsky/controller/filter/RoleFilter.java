package com.matevitsky.controller.filter;

import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "RoleFilter")
public class RoleFilter implements Filter {
    private static final String ALLOWED_PATH = "login change_locale log_out";
    private static final Logger LOGGER = Logger.getLogger(RoleFilter.class);
    private static final String COMMAND = "command";
    private static final String ROLE = "role";
    private static final String USER_ID = "userId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter(COMMAND);
        String role = (String) request.getSession().getAttribute(ROLE);

        if (!ALLOWED_PATH.contains(command)) {
            Object userId = request.getSession().getAttribute(USER_ID);
            if (Objects.isNull(userId)) {
                LOGGER.warn("Attribute userId is null.not logged on user ");
                response.sendRedirect(PageConstant.LOGIN_PAGE);
            } else {
                if (command.contains(role)) {
                    chain.doFilter(request, response);
                } else {
                    LOGGER.warn("User try to reach not relevant content");
                    response.sendRedirect(PageConstant.ERROR_PAGE);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
