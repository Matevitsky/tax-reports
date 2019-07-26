package com.matevitsky.controller.command;

import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_PAGE;

public class InspectorGetNewReportsPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int inspectorId = (int) request.getSession().getAttribute("userId");
        InspectorService inspectorService = new InspectorServiceImpl();
        inspectorService.addNewReportsToRequest(request, inspectorId);

        return INSPECTOR_PAGE;
    }
}