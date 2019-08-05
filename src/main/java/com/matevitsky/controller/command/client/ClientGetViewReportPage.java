package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_VIEW_REPORT_PAGE;

public class ClientGetViewReportPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ClientService clientService = new ClientServiceImpl();

        //TODO:обработаь ошибку
        clientService.addReportToRequest(request, reportId);
        return CLIENT_VIEW_REPORT_PAGE;
    }
}
