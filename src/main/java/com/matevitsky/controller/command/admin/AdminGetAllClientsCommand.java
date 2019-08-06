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
import static com.matevitsky.controller.constant.ParameterConstant.CLIENTS;
import static com.matevitsky.controller.constant.ParameterConstant.INSPECTORS;

public class AdminGetAllClientsCommand implements Command {

    private final ClientService clientService = new ClientServiceImpl();
    private final InspectorService inspectorService = new InspectorServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Client> clientList = new ArrayList<>();
        List<Employee> inspectorList = new ArrayList<>();
        Optional<List<Client>> optionalClients = clientService.getAll();

        if (optionalClients.isPresent()) {
            clientList = optionalClients.get();

            for (Client c : clientList) {
                Optional<Employee> optionalInspector = inspectorService.getById(c.getInspectorId());
                //TODO: заменить на запрос IN sql
                optionalInspector.ifPresent(inspectorList::add);
            }
        }

      /*  optionalClients.ifPresent(clients -> {
            clients.forEach(inspectorService.getById());
        });*/


        request.setAttribute(CLIENTS, clientList);
        request.setAttribute(INSPECTORS, inspectorList);

        adminService.addRequestAmountToHeader(request);

        return ADMIN_CLIENTS_PAGE;
    }
}
