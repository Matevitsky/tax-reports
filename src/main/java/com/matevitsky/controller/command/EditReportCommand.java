package com.matevitsky.controller.command;

import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_ALL_REPORT_PAGE;

public class EditReportCommand implements Command {
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

        List<Report> reportList = reportService.getByClientId(clientId).get();

        request.setAttribute("reports", reportList);

        return CLIENT_ALL_REPORT_PAGE;
    }

    //TODO: подумать- может вынести в отельный сервис создание страницы reports page
}
