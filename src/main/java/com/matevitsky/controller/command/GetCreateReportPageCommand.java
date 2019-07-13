package com.matevitsky.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CREATE_REPORT_PAGE;

public class GetCreateReportPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return CREATE_REPORT_PAGE;
    }
}
