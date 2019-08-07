package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_REPORTS_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.*;

public class AdminClientReportsCommand implements Command {

    private final ReportService reportService;
    private final AdminService adminService;

    public AdminClientReportsCommand(ReportService reportService, AdminService adminService) {
        this.reportService = reportService;
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int clientId = Integer.parseInt(request.getParameter(CLIENT_ID));
        String clientName = request.getParameter(CLIENT_NAME);

        request.getSession().setAttribute(CLIENT_ID, clientId);
        request.getSession().setAttribute(CLIENT_NAME, clientName);

        Optional<List<Report>> optionalReportList = reportService.getReportsByClientId(clientId);
        optionalReportList.ifPresent(reports -> {
            request.setAttribute(REPORTS, reports);
            request.setAttribute(CLIENT_NAME, clientName);
        });


        adminService.addRequestAmountToHeader(request);
        return ADMIN_REPORTS_PAGE;
    }
}
