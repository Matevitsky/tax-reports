package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Request;
import com.matevitsky.service.RequestServiceImpl;
import com.matevitsky.service.interfaces.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientChangeInspectorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RequestService requestService = new RequestServiceImpl();
        int clientId = (int) request.getSession().getAttribute("userId");
        Request changeInspectorRequest = new Request(0, clientId);
        requestService.create(changeInspectorRequest);

        return new GetMainClientPageCommand().execute(request, response);
        //TODO: Notify client about success
    }
}
