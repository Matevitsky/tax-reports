package com.matevitsky.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        return CLIENT_PAGE;
    }

}


