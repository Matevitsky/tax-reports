package com.matevitsky.controller.command;

import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminSaveReport implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String tittle = request.getParameter("tittle");
        String content = request.getParameter("content");


        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);

        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            Report reportForSave = Report.newBuilder()
                .withId(reportId)
                .withTittle(tittle)
                .withContent(content)
                .withClientId(report.getClientId())
                .withStatus(report.getStatus())
                .withreasonToReject(report.getReasonToReject())
                .build();
            reportService.update(reportForSave);
        }
        return new AdminCancelCommand().execute(request, response);
    }
}
