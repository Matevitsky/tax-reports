package com.matevitsky.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;

public class LoginCommand implements Command {

    // private final UserService userService;
    // private final ActivityService activityService;
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

  /*  public LoginCommand(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }*/

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return LOGIN_PAGE;
    }


}


