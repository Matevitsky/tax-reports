package com.matevitsky.controller.command;

import com.matevitsky.entity.User;
import com.matevitsky.service.LoginService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;
import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginService loginService = new LoginService();

        User user = loginService.login(email, password);

        if (user != null) {

            request.getSession().setAttribute("userName", loggedUser.getId());
            return CLIENT_PAGE;
        } else {
            return LOGIN_PAGE;
        }
    }

}


