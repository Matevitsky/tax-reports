package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;

public class GetMainClientPageCommand implements Command {

    public static final String REPORTS = "reports";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int clientId = (int) request.getSession().getAttribute(USER_ID);

        ReportService reportService = new ReportServiceImpl();
        Optional<List<Report>> clientActiveReports = reportService.getClientActiveReports(clientId);
        clientActiveReports.ifPresent(reports -> request.setAttribute(REPORTS, reports));

        return CLIENT_PAGE;
    }
}
