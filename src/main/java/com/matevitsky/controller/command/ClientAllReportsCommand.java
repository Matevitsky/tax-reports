package com.matevitsky.controller.command;

import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_ALL_REPORT_PAGE;

public class ClientAllReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ReportService reportService = new ReportServiceImpl();
        int clientId = (int) request.getSession().getAttribute("userId");

        List<Report> reportList = reportService.getByClientId(clientId).get();
        request.setAttribute("reports", reportList);

        return CLIENT_ALL_REPORT_PAGE;
    }
}
