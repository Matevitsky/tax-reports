package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.interfaces.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.ParameterConstant.CLIENT_ID;
import static com.matevitsky.controller.constant.ParameterConstant.INSPECTOR_ID;

public class AdminAssignInspectorCommand implements Command {

    private final AdminService adminService;

    public AdminAssignInspectorCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int clientId = Integer.parseInt(request.getParameter(CLIENT_ID));
        int inspectorId = Integer.parseInt(request.getParameter(INSPECTOR_ID));

        adminService.assignInspector(clientId, inspectorId);

        //TODO: return message to user from admin service

        return new AdminMainPageCommand(adminService).execute(request, response);
    }
}
