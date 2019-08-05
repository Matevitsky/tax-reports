package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_CLIENTS_PAGE;

public class AdminGetAllClientsCommand implements Command {

    private static final String CLIENTS = "clients";
    private static final String INSPECTORS = "inspectors";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();

        List<Client> clientList = new ArrayList<>();
        List<Employee> inspectorList = new ArrayList<>();
        Optional<List<Client>> optionalClients = clientService.getAll();

        if (optionalClients.isPresent()) {
            clientList = optionalClients.get();
            for (Client c : clientList) {
                Optional<Employee> optionalInspector = inspectorService.getById(c.getInspectorId());
                optionalInspector.ifPresent(inspectorList::add);
            }
        }

        request.setAttribute(CLIENTS, clientList);
        request.setAttribute(INSPECTORS, inspectorList);

        AdminService adminService = new AdminServiceImpl();
        adminService.addRequestAmountToHeader(request);

        return ADMIN_CLIENTS_PAGE;
    }
}
