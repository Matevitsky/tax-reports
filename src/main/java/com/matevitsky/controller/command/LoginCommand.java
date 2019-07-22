package com.matevitsky.controller.command;

import com.matevitsky.entity.User;
import com.matevitsky.service.LoginService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.*;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();
        User user = loginService.login(email, password, request);

        if (user != null && user.getPassword().equals(MD5Util.encryptPassword(password))) {
            request.getSession().setAttribute("userId", user.getId());
            switch (user.getRole()) {
                case CLIENT:
                    return CLIENT_PAGE;
                case INSPECTOR:
                    return INSPECTOR_PAGE;
                case ADMIN:
                    return ADMIN_PAGE;
            }
        }
        LOGGER.info("User with email not exist");
        return LOGIN_PAGE;

    }

}


