package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class ClientDeleteReport implements Command {

    private final ReportService reportService;

    public ClientDeleteReport(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));

        reportService.deleteById(reportId);

        return new GetMainClientPageCommand(reportService).execute(request, response);
    }
}
