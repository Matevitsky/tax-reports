package com.matevitsky.controller.command;

import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CREATE_REPORT_PAGE;

public class ClientGetCreateReportPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ClientService clientService = new ClientServiceImpl();
        clientService.putClientDataToRequest(request);
        return CREATE_REPORT_PAGE;
    }
}
