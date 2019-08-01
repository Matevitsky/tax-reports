package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.REGISTRATION_PAGE;

public class GetRegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return REGISTRATION_PAGE;
    }
}
