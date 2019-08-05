package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_EDIT_REPORT_PAGE;

public class ClientGetEditReportPageCommand implements Command {

    private static final String REPORT_ID = "reportId";
    private static final String REPORT = "report";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {

            Report reportForUpdate = optionalReport.get();
            request.setAttribute(REPORT, reportForUpdate);
        }
        return CLIENT_EDIT_REPORT_PAGE;
    }
}
