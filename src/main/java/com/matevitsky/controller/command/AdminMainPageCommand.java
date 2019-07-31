package com.matevitsky.controller.command;

import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.interfaces.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminMainPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AdminService adminService = new AdminServiceImpl();
        adminService.prepareAdminPage(request);
        return ADMIN_PAGE;
    }
}
