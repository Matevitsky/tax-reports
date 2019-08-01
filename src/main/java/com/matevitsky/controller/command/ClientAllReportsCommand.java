package com.matevitsky.controller.command;

import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_ALL_REPORT_PAGE;

public class ClientAllReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ClientService clientService = new ClientServiceImpl();
        clientService.putClientDataToRequest(request);
        //TODO: вернуть ответ пользователю если требуется

        return CLIENT_ALL_REPORT_PAGE;
    }
}
