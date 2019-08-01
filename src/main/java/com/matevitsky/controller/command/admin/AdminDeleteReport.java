package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteReport implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ReportService reportService = new ReportServiceImpl();
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        reportService.deleteById(reportId);

        return new AdminCancelCommand().execute(request, response);
    }
}
