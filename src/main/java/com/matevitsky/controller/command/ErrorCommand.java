package com.matevitsky.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ERROR_PAGE;

public class ErrorCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return ERROR_PAGE;
    }
}
