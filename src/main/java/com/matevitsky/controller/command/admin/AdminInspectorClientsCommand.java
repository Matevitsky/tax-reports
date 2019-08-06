package com.matevitsky.controller.command.admin;

import com.matevitsky.controller.command.Command;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.AdminServiceImpl;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
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

        Employee inspector = Employee.newBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        Optional<List<Client>> optionalClients = clientService.getClientsByInspectorId(inspectorId);

        if (optionalClients.isPresent()) {
            List<Employee> inspectorList = Collections.nCopies(optionalClients.get().size(), inspector);

            //TODO: переделать на фронте имя инспектора

            request.setAttribute(CLIENTS, optionalClients.get());
            request.setAttribute(INSPECTORS, inspectorList);
        }

        return ADMIN_CLIENTS_PAGE;
    }
}
