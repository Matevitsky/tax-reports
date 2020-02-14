package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;

public class ClientEditReportCommand implements Command {

    private final ReportService reportService;

    public ClientEditReportCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        String reportTittle = request.getParameter(TITTLE);
        String reportContent = request.getParameter(CONTENT);
        int clientId = (int) request.getSession().getAttribute(USER_ID);

        Report report = Report.newBuilder()
                .withId(reportId)
                .withTittle(reportTittle)
                .withContent(reportContent)
                .withStatus(ReportStatus.NEW)
                .withClientId(clientId)
                .build();

        reportService.update(report);

        Optional<List<Report>> optionalReportList = reportService.getReportsByClientId(clientId);
        optionalReportList.ifPresent(reports -> request.setAttribute(REPORTS, reports));

        return new GetMainClientPageCommand(reportService).execute(request, response);
    }

}
