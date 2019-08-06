package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_REPORT_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class InspectorGetNewReportPageCommand implements Command {

    private final ReportService reportService = new ReportServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));

        Optional<Report> optionalReport = reportService.getById(reportId);
        Report report = null;

        if (optionalReport.isPresent()) {
            report = reportService.changeStatusToInProgress(optionalReport.get());
        }

        request.setAttribute(REPORT, report);

        return INSPECTOR_REPORT_PAGE;
    }
}
