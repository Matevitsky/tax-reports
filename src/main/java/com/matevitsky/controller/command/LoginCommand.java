package com.matevitsky.controller.command;

import com.matevitsky.dto.UserForLogin;
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
        String encryptedPassword = MD5Util.encryptPassword(password);
        LoginService loginService = new LoginService();
        UserForLogin user = loginService.login(email, encryptedPassword, request);

        if (user != null) {
            if (user.getPassword().equals(encryptedPassword)) {
                request.getSession().setAttribute("userId", user.getId());
                switch (user.getRole()) {
                    case ADMIN:
                        return ADMIN_PAGE;
                    case INSPECTOR:
                        return INSPECTOR_PAGE;
                    case CLIENT:
                        return new GetClientPageCommand().execute(request, response);
                }
            }
        }
        LOGGER.info("User with email not exist");
        return LOGIN_PAGE;

    }
}


