package com.matevitsky.controller.command.client;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.service.interfaces.ReportService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CREATE_REPORT_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.*;

public class ClientCreateReportCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ClientCreateReportCommand.class);

    private final ReportService reportService;

    public ClientCreateReportCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tittle = request.getParameter(TITTLE);
        String content = request.getParameter(CONTENT);
        if (tittle.isEmpty() || content.isEmpty()) {
            LOGGER.info("tittle or content is empty");
            return CREATE_REPORT_PAGE;
        }
        int clientId = (int) request.getSession().getAttribute(USER_ID);

        Report report = Report.newBuilder()
                .withTittle(tittle)
                .withContent(content)
                .withStatus(ReportStatus.NEW)
                .withreasonToReject("")
                .withClientId(clientId)
                .build();

        reportService.create(report);

        return new GetMainClientPageCommand(reportService).execute(request, response);
    }
}
