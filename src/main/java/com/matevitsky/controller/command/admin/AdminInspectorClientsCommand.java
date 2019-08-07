package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.dto.ClientForAdmin;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_CLIENTS_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.*;

public class AdminInspectorClientsCommand implements Command {

    private final ClientService clientService = new ClientServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        adminService.addRequestAmountToHeader(request);

        int inspectorId = Integer.parseInt(request.getParameter(INSPECTOR_ID));
        String firstName = request.getParameter(INSPECTOR_FIRST_NAME);
        String lastName = request.getParameter(INSPECTOR_LAST_NAME);


        Optional<List<ClientForAdmin>> optionalClients = clientService.getClientsForAdminByInspectorId(inspectorId);

        optionalClients.ifPresent(clientForAdmins -> request.setAttribute(CLIENTS, clientForAdmins));

        return ADMIN_CLIENTS_PAGE;
    }
}
