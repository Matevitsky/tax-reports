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

public class InspectorGetAllReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int inspectorId = (int) request.getSession().getAttribute("userId");
        InspectorService inspectorService = new InspectorServiceImpl();

        Optional<List<ReportWithClientName>> reports = inspectorService.getReports(inspectorId);
        reports.ifPresent(reportWithClientNames -> request.setAttribute("reports", reportWithClientNames));
        inspectorService.addDataToRequest(request, inspectorId);

        return INSPECTOR_PAGE;
    }
}