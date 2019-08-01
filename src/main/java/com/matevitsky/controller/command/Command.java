package com.matevitsky.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.REGISTRATION_PAGE;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response);

    class GetRegistrationPageCommand implements Command {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {

            return REGISTRATION_PAGE;
        }
    }
}

