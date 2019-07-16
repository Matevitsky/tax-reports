package com.matevitsky.controller.command;

import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.service.ReportService;
import com.matevitsky.service.ReportServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.CLIENT_PAGE;
import static com.matevitsky.controller.constant.PageConstant.CREATE_REPORT_PAGE;

public class CreateReportCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateReportCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String tittle = request.getParameter("tittle");
        String content = request.getParameter("content");
        if (tittle.isEmpty() || content.isEmpty()) {
            LOGGER.info("tittle or content is empty");
            return CREATE_REPORT_PAGE;
        }

        int clientId = (int) request.getSession().getAttribute("userId");

        Report report = Report.newBuilder()
                .withTittle(tittle)
                .withContent(content)
                .withStatus(ReportStatus.NEW)
                .withClientId(clientId)
                .build();
        ReportService reportService = new ReportServiceImpl();
        reportService.create(report);

        return CLIENT_PAGE;
    }
}
