package com.matevitsky.controller.command;


import com.matevitsky.controller.command.client.GetMainClientPageCommand;
import com.matevitsky.entity.Client;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.ReportService;
import com.matevitsky.util.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;

public class RegisterCommand implements Command {

    private final ClientService clientService;
    private final ReportService reportService;

    public RegisterCommand(ClientService clientService, ReportService reportService) {
        this.clientService = clientService;
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String emailAddress = request.getParameter(EMAIL_ADDRESS);
        String password = request.getParameter(PASSWORD);
        int companyId = Integer.parseInt(request.getParameter(COMPANY_ID));

        Client client = Client.newClientBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(emailAddress)
                .withPassword(MD5Util.encryptPassword(password))
                .withCompanyId(companyId)
                .build();
        Client clientWithInspector = clientService.assignInspector(client);
        Optional<Client> optionalClient = clientService.register(clientWithInspector);

        /*   request.setAttribute("Registration error", e.getMessage());*/

        optionalClient.ifPresent(value -> request.getSession().setAttribute(USER_ID, value.getId()));

        return new GetMainClientPageCommand(reportService).execute(request, response);
    }
}
