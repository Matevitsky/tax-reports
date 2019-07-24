package com.matevitsky.controller.command;

import com.matevitsky.entity.Inspector;
import com.matevitsky.entity.Report;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_PAGE;

public class InspectorGetNewReportsPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int inspectorId = (int) request.getSession().getAttribute("userId");

        InspectorService inspectorService = new InspectorServiceImpl();
        Optional<Inspector> inspectorById = inspectorService.getById(inspectorId);
        if (inspectorById.isPresent()) {
            Inspector inspector = inspectorById.get();
            ReportService reportService = new ReportServiceImpl();
            Optional<List<Report>> reportsByClientId = reportService.getInspectorNewReports(inspector.getClientId());
            if (reportsByClientId.isPresent()) {
                request.setAttribute("reports", reportsByClientId.get());
            }
        }
        return INSPECTOR_PAGE;
    }
}
