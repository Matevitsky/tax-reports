package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.interfaces.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_PAGE;

public class AdminMainPageCommand implements Command {

    private final AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        adminService.prepareAdminPage(request);
        return ADMIN_PAGE;
    }
}
