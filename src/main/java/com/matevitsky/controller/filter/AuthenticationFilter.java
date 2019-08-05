package com.matevitsky.controller.filter;

import com.matevitsky.controller.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        LOGGER.warn("AuthenticationFilter started");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        if (uri.contains("/app")) {
            chain.doFilter(request, response);
        } else {
            LOGGER.warn("User try to reach not existing page");

            response.sendRedirect(PageConstant.ERROR_PAGE);
        }
    }

}
