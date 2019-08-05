package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_REPORTS_PAGE;

public class AdminCancelCommand implements Command {

    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_NAME = "clientName";
    private static final String REPORTS = "reports";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ReportService reportService = new ReportServiceImpl();
        int clientId = (int) request.getSession().getAttribute(CLIENT_ID);
        String clientName = (String) request.getSession().getAttribute(CLIENT_NAME);

        Optional<List<Report>> optionalReportList = reportService.getReportsByClientId(clientId);
        optionalReportList.ifPresent(reports -> {
            request.setAttribute(REPORTS, reports);
            request.setAttribute(CLIENT_NAME, clientName);
        });

        AdminService adminService = new AdminServiceImpl();
        adminService.addRequestAmountToHeader(request);

        return ADMIN_REPORTS_PAGE;
    }
}
