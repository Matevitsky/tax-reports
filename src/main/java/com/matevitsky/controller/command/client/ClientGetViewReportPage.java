package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_VIEW_REPORT_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class ClientGetViewReportPage implements Command {

    private final ClientService clientService;

    public ClientGetViewReportPage(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));

        clientService.addReportToRequest(request, reportId);

        return CLIENT_VIEW_REPORT_PAGE;
    }
}
