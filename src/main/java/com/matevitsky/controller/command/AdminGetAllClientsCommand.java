package com.matevitsky.controller.command;

import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_CLIENTS_PAGE;

public class AdminGetAllClientsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ClientService clientService = new ClientServiceImpl();
        request.setAttribute("clients", clientService.getAll().get());
        return ADMIN_CLIENTS_PAGE;
    }
}
