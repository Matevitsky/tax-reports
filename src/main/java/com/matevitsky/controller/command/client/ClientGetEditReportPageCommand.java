package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_EDIT_REPORT_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class ClientGetEditReportPageCommand implements Command {

    private final ReportService reportService;

    public ClientGetEditReportPageCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));

        Optional<Report> optionalReport = reportService.getById(reportId);

        optionalReport.ifPresent(report -> {
            request.setAttribute(REPORT, report);
        });

        return CLIENT_EDIT_REPORT_PAGE;
    }
}
