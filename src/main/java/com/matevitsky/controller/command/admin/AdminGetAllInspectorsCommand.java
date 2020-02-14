package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_INSPECTORS_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.INSPECTORS;

public class AdminGetAllInspectorsCommand implements Command {

    private final InspectorService inspectorService;
    private final AdminService adminService;

    public AdminGetAllInspectorsCommand(InspectorService inspectorService, AdminService adminService) {
        this.inspectorService = inspectorService;
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        inspectorService.getAll().ifPresent(employees -> request.setAttribute(INSPECTORS, employees));

        adminService.addRequestAmountToHeader(request);

        return ADMIN_INSPECTORS_PAGE;
    }
}
