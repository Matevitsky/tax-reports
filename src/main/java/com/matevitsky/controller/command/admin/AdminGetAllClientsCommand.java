package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.dto.ClientForAdmin;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_CLIENTS_PAGE;
import static com.matevitsky.controller.constant.ParameterConstant.CLIENTS;

public class AdminGetAllClientsCommand implements Command {

    private final ClientService clientService;
    private final AdminService adminService;

    public AdminGetAllClientsCommand(ClientService clientService, AdminService adminService) {
        this.clientService = clientService;
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        Optional<List<ClientForAdmin>> allClientsForInspector = clientService.getAllClientsForInspector();

        allClientsForInspector.ifPresent(clients -> request.setAttribute(CLIENTS, clients));

        adminService.addRequestAmountToHeader(request);

        return ADMIN_CLIENTS_PAGE;
    }
}
