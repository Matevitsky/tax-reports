package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CREATE_REPORT_PAGE;

public class ClientGetCreateReportPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return CREATE_REPORT_PAGE;
    }
}
