package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteReport implements Command {

    private static final String REPORT_ID = "reportId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ReportService reportService = new ReportServiceImpl();
        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        reportService.deleteById(reportId);

        return new AdminCancelCommand().execute(request, response);
    }
}
