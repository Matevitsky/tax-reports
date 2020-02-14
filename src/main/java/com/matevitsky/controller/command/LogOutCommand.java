package com.matevitsky.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.LOGIN_PAGE;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();

        return LOGIN_PAGE;
    }
}
