package com.matevitsky.controller.command;

import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_REPORTS_PAGE;

public class AdminCancelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ReportService reportService = new ReportServiceImpl();
        int clientId = (int) request.getSession().getAttribute("clientId");
        String clientName = (String) request.getSession().getAttribute("clientName");


        Optional<List<Report>> optionalReportList = reportService.getByClientId(clientId);
        if (optionalReportList.isPresent()) {
            request.setAttribute("reports", optionalReportList.get());
            request.setAttribute("clientName", clientName);
        }


        return ADMIN_REPORTS_PAGE;
    }
}
