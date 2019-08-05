package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InspectorDeclineReportCommand implements Command {

    private static final String REPORT_ID = "reportId";
    private static final String REASON_TO_REJECT = "reasonToReject";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        String reasonToReject = request.getParameter(REASON_TO_REJECT);

        InspectorService inspectorService = new InspectorServiceImpl();
        inspectorService.declineReport(reportId, reasonToReject);


        return new InspectorGetAllReportsCommand().execute(request, response);
    }
}
