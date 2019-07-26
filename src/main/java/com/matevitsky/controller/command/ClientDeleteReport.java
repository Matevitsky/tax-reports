package com.matevitsky.controller.command;

import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientDeleteReport implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ReportService reportService = new ReportServiceImpl();
        reportService.deleteById(reportId);
        return new GetClientPageCommand().execute(request, response);
    }
}
