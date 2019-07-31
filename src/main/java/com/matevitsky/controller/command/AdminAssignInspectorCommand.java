package com.matevitsky.controller.command;

import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.interfaces.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminAssignInspectorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AdminService adminService = new AdminServiceImpl();

        int clientId = Integer.parseInt(request.getParameter("clientId"));
        int inspectorId = Integer.parseInt(request.getParameter("inspectorId"));

        adminService.assignInspector(clientId, inspectorId);

        //TODO: return message to user from admin service
        adminService.prepareAdminPage(request);
        return ADMIN_PAGE;
    }
}
