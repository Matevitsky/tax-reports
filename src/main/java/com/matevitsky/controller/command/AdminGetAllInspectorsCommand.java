package com.matevitsky.controller.command;

import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_INSPECTORS_PAGE;

public class AdminGetAllInspectorsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        InspectorService inspectorService = new InspectorServiceImpl();
        request.setAttribute("inspectors", inspectorService.getAll().get());
        return ADMIN_INSPECTORS_PAGE;
    }
}
