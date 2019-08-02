package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_ALL_REPORT_PAGE;

public class ClientAllReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int clientId = (int) request.getSession().getAttribute("userId");
        ReportService reportService = new ReportServiceImpl();
        Optional<List<Report>> clientActiveReports = reportService.getReportsByClientId(clientId);
        clientActiveReports.ifPresent(reports -> request.setAttribute("reports", reports));


        return CLIENT_ALL_REPORT_PAGE;
    }
}
