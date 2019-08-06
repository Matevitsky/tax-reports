package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.ReportServiceImpl;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.ParameterConstant.REPORT_ID;

public class AdminDeleteReport implements Command {

    private final ReportService reportService = new ReportServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int reportId = Integer.parseInt(request.getParameter(REPORT_ID));
        reportService.deleteById(reportId);

        return new AdminCancelCommand().execute(request, response);
    }
}
