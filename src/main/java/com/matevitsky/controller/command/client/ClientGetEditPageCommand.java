package com.matevitsky.controller.command.client;

/*
public class ClientGetEditPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {

            Report reportForUpdate = optionalReport.get();
            request.setAttribute("report", reportForUpdate);
        }
        return EDIT_VIEW_REPORT_PAGE;
    }
}
*/
