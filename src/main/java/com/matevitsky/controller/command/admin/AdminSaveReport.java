package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;

public class AdminSaveReport implements Command {

    private final ReportService reportService;
    private final AdminService adminService;

    public AdminSaveReport(ReportService reportService, AdminService adminService) {
        this.reportService = reportService;
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        String tittle = request.getParameter(TITTLE);
        String content = request.getParameter(CONTENT);

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
        return new AdminCancelCommand(reportService, adminService).execute(request, response);
    }
}
