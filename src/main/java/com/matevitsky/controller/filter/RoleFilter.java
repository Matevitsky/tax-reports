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
    public static final String ALLOWED_PATH = "login change_locale log_out";
    private static final Logger LOGGER = Logger.getLogger(RoleFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        LOGGER.warn("Role started");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter("command");
        String role = (String) request.getSession().getAttribute("role");

        LOGGER.warn(request.getRequestURI());
        if (!ALLOWED_PATH.contains(command)) {
            Object userId = request.getSession().getAttribute("userId");
            if (Objects.isNull(userId)) {
                response.sendRedirect(PageConstant.LOGIN_PAGE);
            } else {
                if (command.contains(role)) {
                    chain.doFilter(request, response);
                } else {
                    response.sendRedirect(PageConstant.ERROR_PAGE);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
