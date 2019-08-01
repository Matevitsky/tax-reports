package com.matevitsky.controller.command;

import com.matevitsky.entity.Employee;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;

public class GetClientPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //TODO: создать сервис для создания страниц клиента и испектора

        int clientId = (int) request.getSession().getAttribute("userId");
        ReportService reportService = new ReportServiceImpl();
        Optional<List<Report>> clientActiveReports = reportService.getClientActiveReports(clientId);
        ClientService clientService = new ClientServiceImpl();
        Optional<Employee> optionalInspector = clientService.getInspector(clientId);
        String clientName = clientService.getNameById(clientId);

        request.setAttribute("clientName", clientName);

        if (optionalInspector.isPresent()) {
            request.setAttribute("inspector", optionalInspector.get());
        }

        if (clientActiveReports.isPresent()) {
            request.setAttribute("reports", clientActiveReports.get());
        }
        return CLIENT_PAGE;
    }
}
