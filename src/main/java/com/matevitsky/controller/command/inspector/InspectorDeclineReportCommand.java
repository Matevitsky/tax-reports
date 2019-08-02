package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InspectorDeclineReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String reasonToReject = request.getParameter("reasonToReject");

        InspectorService inspectorService = new InspectorServiceImpl();
        inspectorService.declineReport(reportId, reasonToReject);


        return new InspectorGetAllReportsCommand().execute(request, response);
    }
}
