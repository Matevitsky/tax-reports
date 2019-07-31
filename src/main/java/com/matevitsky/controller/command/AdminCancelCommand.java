package com.matevitsky.controller.command;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_REPORTS_PAGE;

public class AdminCancelCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {
            int clientId = optionalReport.get().getClientId();
            ClientService clientService = new ClientServiceImpl();
            Optional<Client> optionalClient = clientService.getById(clientId);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                Optional<List<Report>> optionalReportList = reportService.getByClientId(client.getId());
                if (optionalReportList.isPresent()) {
                    request.setAttribute("reports", optionalReportList.get());
                    request.setAttribute("clientName", client.getFirstName() + " " + client.getLastName());
                }
            }
        }

        return ADMIN_REPORTS_PAGE;
    }
}
