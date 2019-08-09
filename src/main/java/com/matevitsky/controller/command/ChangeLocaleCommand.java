package com.matevitsky.controller.command;

import com.matevitsky.controller.command.admin.AdminMainPageCommand;
import com.matevitsky.controller.command.client.GetMainClientPageCommand;
import com.matevitsky.controller.command.inspector.InspectorGetNewReportsCommand;
import com.matevitsky.service.ResourceManager;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;

import static com.matevitsky.controller.constant.ParameterConstant.*;


public class ChangeLocaleCommand implements Command {
    private final ReportService reportService;
    private final AdminService adminService;
    private final InspectorService inspectorService;

    public ChangeLocaleCommand(ReportService reportService, AdminService adminService, InspectorService inspectorService) {
        this.reportService = reportService;
        this.adminService = adminService;
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String locale = request.getParameter(LOCALE);
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute(LOCALE, locale);

        String role = (String) request.getSession().getAttribute(ROLE);

        if (Objects.nonNull(role)) {
            switch (role) {
                case CLIENT:
                    return new GetMainClientPageCommand(reportService).execute(request, response);
                case ADMIN:
                    return new AdminMainPageCommand(adminService).execute(request, response);
                case INSPECTOR:
                    return new InspectorGetNewReportsCommand(inspectorService).execute(request, response);
            }
        }
        return request.getParameter(URI);

    }

}
