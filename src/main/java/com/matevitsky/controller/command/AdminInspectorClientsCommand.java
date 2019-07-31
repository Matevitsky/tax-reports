package com.matevitsky.controller.command;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.PageConstant.ADMIN_CLIENTS_PAGE;

public class AdminInspectorClientsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ClientService clientService = new ClientServiceImpl();


        int inspectorId = Integer.parseInt(request.getParameter("inspectorId"));
        String firstName = request.getParameter("inspectorFirstName");
        String lastName = request.getParameter("inspectorLastName");
        Employee inspector = Employee.newBuilder()
            .withFirstName(firstName)
            .withLastName(lastName)
            .build();

        Optional<List<Client>> optionalClients = clientService.getClientsByInspectorId(inspectorId);
        if (optionalClients.isPresent()) {
            List<Employee> inspectorList = Collections.nCopies(optionalClients.get().size(), inspector);

            request.setAttribute("clients", optionalClients.get());
            request.setAttribute("inspectors", inspectorList);

        }
        return ADMIN_CLIENTS_PAGE;
    }
}
