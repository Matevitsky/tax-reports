package com.matevitsky.controller.command;

import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;

public class GetClientPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //TODO: создать сервис для создания страниц клиента и испектора
        ClientService clientService = new ClientServiceImpl();
        clientService.putClientDataToRequest(request);
        return CLIENT_PAGE;
    }
}
