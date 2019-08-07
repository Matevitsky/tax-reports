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
import static com.matevitsky.controller.constant.ParameterConstant.EMAIL;
import static com.matevitsky.controller.constant.ParameterConstant.PASSWORD;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private final LoginService loginService = new LoginService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String encryptedPassword = MD5Util.encryptPassword(password);

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
        LOGGER.info("User with email " + email + " not exist");

        return LOGIN_PAGE;
    }
}


