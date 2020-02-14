package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class AdminDeleteReport implements Command {

    private final ReportService reportService;
    private final AdminService adminService;

    public AdminDeleteReport(ReportService reportService, AdminService adminService) {
        this.reportService = reportService;
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        reportService.deleteById(reportId);

        return new AdminCancelCommand(reportService, adminService).execute(request, response);
    }
}
