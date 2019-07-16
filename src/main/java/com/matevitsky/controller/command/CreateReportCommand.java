package com.matevitsky.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateReportCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateReportCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tittle = request.getParameter("tittle");
        String content = request.getParameter("content");
        int userId = (int) request.getSession().getAttribute("userId");
        return null;
    }
}
