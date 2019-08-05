package com.matevitsky.controller.command;

import com.matevitsky.controller.command.admin.AdminMainPageCommand;
import com.matevitsky.controller.command.client.GetMainClientPageCommand;
import com.matevitsky.controller.command.inspector.InspectorGetNewReportsCommand;
import com.matevitsky.dto.UserForLogin;
import com.matevitsky.service.LoginService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String encryptedPassword = MD5Util.encryptPassword(password);
        LoginService loginService = new LoginService();

        UserForLogin user = loginService.login(email, encryptedPassword, request);

        if (user != null && user.getPassword().equals(encryptedPassword)) {
            switch (user.getRole()) {
                case ADMIN:
                    return new AdminMainPageCommand().execute(request, response);
                case INSPECTOR:
                    return new InspectorGetNewReportsCommand().execute(request, response);
                case CLIENT:
                    return new GetMainClientPageCommand().execute(request, response);
            }
        }
        LOGGER.info("User with email" + email + " not exist");
        return LOGIN_PAGE;

    }
}


