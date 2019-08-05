package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_PAGE;

public class InspectorGetNewReportsCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String REPORTS = "reports";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int inspectorId = (int) request.getSession().getAttribute(USER_ID);
        InspectorService inspectorService = new InspectorServiceImpl();

        Optional<List<ReportWithClientName>> optionalReports = inspectorService.getNewReports(inspectorId);
        optionalReports.ifPresent(reportWithClientNames -> request.setAttribute(REPORTS, reportWithClientNames));

        return INSPECTOR_PAGE;
    }
}
