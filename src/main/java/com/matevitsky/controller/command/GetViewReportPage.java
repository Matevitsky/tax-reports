package com.matevitsky.controller.command;

import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_REPORT_PAGE;

public class GetViewReportPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ClientService clientService = new ClientServiceImpl();

        //TODO:обработаь ошибку
        clientService.addReportToRequest(request, reportId);
        return CLIENT_REPORT_PAGE;
    }
}
