package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Request;
import com.matevitsky.service.interfaces.ReportService;
import com.matevitsky.service.interfaces.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.ParameterConstant.USER_ID;

public class ClientChangeInspectorCommand implements Command {

    private final RequestService requestService;
    private final ReportService reportService;

    public ClientChangeInspectorCommand(RequestService requestService, ReportService reportService) {
        this.requestService = requestService;
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int clientId = (int) request.getSession().getAttribute(USER_ID);

        Request changeInspectorRequest = new Request(0, clientId);

        boolean b = requestService.create(changeInspectorRequest);

        request.setAttribute("result", b);

        return new GetMainClientPageCommand(reportService).execute(request, response);

    }
}
