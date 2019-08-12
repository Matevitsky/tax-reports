package com.matevitsky.controller.command;


import com.matevitsky.controller.command.client.GetMainClientPageCommand;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Company;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.CompanyService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;
import com.matevitsky.util.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;
import static java.util.Locale.ENGLISH;

public class RegisterCommand implements Command {


    private final ClientService clientService;
    private final ReportService reportService;
    private final CompanyService companyService;
    private final InspectorService inspectorService;

    public RegisterCommand(ClientService clientService, ReportService reportService, CompanyService companyService, InspectorService inspectorService) {
        this.clientService = clientService;
        this.reportService = reportService;
        this.companyService = companyService;
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String emailAddress = request.getParameter(EMAIL_ADDRESS);
        String password = request.getParameter(PASSWORD);
        String companyName = request.getParameter(COMPANY_NAME);
        int companyId = 0;

        if (!companyName.isEmpty()) {
            companyService.create(new Company(0, companyName));
            Optional<Company> optionalCompany = companyService.getByCompanyByName(companyName);
            if (optionalCompany.isPresent()) {
                companyId = optionalCompany.get().getId();
            }

        }

        Client client = Client.newClientBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(emailAddress)
                .withPassword(MD5Util.encryptPassword(password))
                .withCompanyId(companyId)
                .build();

        Optional<Client> optionalRegisteredClient = clientService.register(client);

        if (optionalRegisteredClient.isPresent()) {
            client = optionalRegisteredClient.get();
            request.getSession().setAttribute(USER_ID, client.getId());
            request.getSession().setAttribute(LOCALE, ENGLISH);
            request.getSession().setAttribute(USER_ID, client.getId());
            request.getSession().setAttribute(ROLE, CLIENT);
            request.getSession().setAttribute(CLIENT_NAME, client.getFirstName() + " " + client.getLastName());
            Optional<Employee> inspector = inspectorService.getById(client.getInspectorId());
            inspector.ifPresent(employee -> request.getSession().setAttribute(INSPECTOR, employee));

        } else {
            return null;
        }

        return new GetMainClientPageCommand(reportService).execute(request, response);
    }
}
