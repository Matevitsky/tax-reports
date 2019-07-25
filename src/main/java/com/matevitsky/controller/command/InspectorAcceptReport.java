package com.matevitsky.controller.command;

import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_PAGE;

public class InspectorAcceptReport implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        int inspectorId = (int) request.getSession().getAttribute("userId");
        InspectorService inspectorService = new InspectorServiceImpl();
        boolean b = inspectorService.acceptReport(reportId);
        //TODO: вурнуть сообщение пользователю об успешной операции

        inspectorService.addNewReportsToRequest(request, inspectorId);

        return INSPECTOR_PAGE;
    }
}
