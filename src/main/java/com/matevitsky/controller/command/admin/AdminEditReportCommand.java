package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_EDIT_REPORTS_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class AdminEditReportCommand implements Command {

    private final ReportService reportService = new ReportServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));

        Optional<Report> optionalReport = reportService.getById(reportId);
        optionalReport.ifPresent(report -> request.setAttribute(REPORT, report));

        adminService.addRequestAmountToHeader(request);

        return ADMIN_EDIT_REPORTS_PAGE;
    }
}
