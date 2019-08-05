package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InspectorAcceptReportCommand implements Command {

    private static final String REPORT_ID = "reportId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));

        InspectorService inspectorService = new InspectorServiceImpl();
        inspectorService.acceptReport(reportId);

        return new InspectorGetAllReportsCommand().execute(request, response);
    }
}
