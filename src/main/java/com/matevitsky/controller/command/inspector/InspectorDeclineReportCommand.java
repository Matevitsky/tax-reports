package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.ParameterConstant.REASON_TO_REJECT;
import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class InspectorDeclineReportCommand implements Command {

    private final InspectorService inspectorService = new InspectorServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        String reasonToReject = request.getParameter(REASON_TO_REJECT);

        inspectorService.declineReport(reportId, reasonToReject);


        return new InspectorGetAllReportsCommand().execute(request, response);
    }
}
