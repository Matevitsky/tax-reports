package com.matevitsky.controller.command.inspector;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.INSPECTOR_REPORT_PAGE;


public class InspectorGetReportPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);

        request.setAttribute("report", optionalReport.get());

       /* int inspectorId = (int) request.getSession().getAttribute("userId");
        InspectorService inspectorService = new InspectorServiceImpl();
      //  inspectorService.addDataToRequest(request, inspectorId);*/

        return INSPECTOR_REPORT_PAGE;
    }
}
