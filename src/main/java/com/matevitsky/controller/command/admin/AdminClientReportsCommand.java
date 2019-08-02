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

public class AdminClientReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int clientId = Integer.parseInt(request.getParameter("clientId"));
        String clientName = request.getParameter("clientName");

        request.getSession().setAttribute("clientId", clientId);
        request.getSession().setAttribute("clientName", clientName);


        ReportService reportService = new ReportServiceImpl();
        Optional<List<Report>> optionalReportList = reportService.getReportsByClientId(clientId);
        if (optionalReportList.isPresent()) {

            request.setAttribute("reports", optionalReportList.get());
            request.setAttribute("clientName", clientName);
        }
        AdminService adminService = new AdminServiceImpl();
        adminService.addHeaderDataToRequest(request);
        return ADMIN_REPORTS_PAGE;
    }
}
