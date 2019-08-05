package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_INSPECTORS_PAGE;

public class AdminGetAllInspectorsCommand implements Command {

    private static final String INSPECTORS = "inspectors";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        InspectorService inspectorService = new InspectorServiceImpl();
        AdminService adminService = new AdminServiceImpl();
        inspectorService.getAll().ifPresent(employees -> request.setAttribute(INSPECTORS, employees));

        adminService.addRequestAmountToHeader(request);

        return ADMIN_INSPECTORS_PAGE;
    }
}
