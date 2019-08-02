package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientEditReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String reportTittle = request.getParameter("tittle");
        String reportContent = request.getParameter("content");
        int clientId = (int) request.getSession().getAttribute("userId");
        ReportService reportService = new ReportServiceImpl();
        Report report = Report.newBuilder()
            .withId(reportId)
            .withTittle(reportTittle)
            .withContent(reportContent)
            .withStatus(ReportStatus.NEW)
            .withClientId(clientId)
            .build();

        reportService.update(report);

        List<Report> reportList = reportService.getReportsByClientId(clientId).get();

        request.setAttribute("reports", reportList);

        return new GetClientPageCommand().execute(request, response);
    }

    //TODO: подумать- может вынести в отельный сервис создание страницы reports page
}
