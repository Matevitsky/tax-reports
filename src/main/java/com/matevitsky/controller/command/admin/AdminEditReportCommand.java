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

public class AdminEditReportCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {
            Report reportForUpdate = optionalReport.get();
            request.setAttribute("report", reportForUpdate);
        }

        AdminService adminService = new AdminServiceImpl();
        adminService.addHeaderDataToRequest(request);

        return ADMIN_EDIT_REPORTS_PAGE;
    }
}
