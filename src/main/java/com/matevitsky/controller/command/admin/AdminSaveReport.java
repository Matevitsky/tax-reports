package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminSaveReport implements Command {

    private static final String REPORT_ID = "reportId";
    private static final String TITTLE = "tittle";
    private static final String CONTENT = "content";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        String tittle = request.getParameter(TITTLE);
        String content = request.getParameter(CONTENT);


        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);

        optionalReport.ifPresent(value -> {
            Report report = value;
            Report reportForSave = Report.newBuilder()
                    .withId(reportId)
                    .withTittle(tittle)
                    .withContent(content)
                    .withClientId(report.getClientId())
                    .withStatus(report.getStatus())
                    .withreasonToReject(report.getReasonToReject())
                    .build();
            reportService.update(reportForSave);
        });
        return new AdminCancelCommand().execute(request, response);
    }
}
