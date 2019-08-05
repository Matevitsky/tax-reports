package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientDeleteReport implements Command {

    private static final String REPORT_ID = "reportId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        ReportService reportService = new ReportServiceImpl();
        reportService.deleteById(reportId);

        return new GetMainClientPageCommand().execute(request, response);
    }
}
