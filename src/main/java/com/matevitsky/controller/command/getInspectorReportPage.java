package com.matevitsky.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_REPORT_PAGE;


public class getInspectorReportPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int inspectorId = (int) request.getSession().getAttribute("userId");

        return INSPECTOR_REPORT_PAGE;
    }
}
