package com.matevitsky.controller.command;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.InspectorServiceImpl;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_CLIENTS_PAGE;

public class AdminGetAllClientsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();

        List<Client> clientList = new ArrayList<>();
        List<Employee> inspectorList = new ArrayList<>();
        Optional<List<Client>> optionalClients = clientService.getAll();
        if (optionalClients.isPresent()) {
            clientList = optionalClients.get();
        }
        for (Client c : clientList) {
            Optional<Employee> optionalInspector = inspectorService.getById(c.getInspectorId());
            if (optionalInspector.isPresent()) {
                inspectorList.add(optionalInspector.get());

            }
        }
        request.setAttribute("clients", clientList);
        request.setAttribute("inspectors", inspectorList);
        return ADMIN_CLIENTS_PAGE;
    }
}
