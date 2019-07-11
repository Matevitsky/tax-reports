package com.matevitsky.controller.command;

import com.matevitsky.db.ConnectorDB;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;

public class LoginCommand implements Command {

    // private final UserService userService;
    // private final ActivityService activityService;

    private ConnectorDB pool;
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

  /*  public LoginCommand(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }*/

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        return CLIENT_PAGE;
    }


}


