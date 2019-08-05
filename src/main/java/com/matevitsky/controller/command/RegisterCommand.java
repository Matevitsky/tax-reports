package com.matevitsky.controller.command;


import com.matevitsky.controller.command.client.GetMainClientPageCommand;
import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.util.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RegisterCommand implements Command {


    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL_ADDRESS = "emailAddress";
    private static final String PASSWORD = "password";
    private static final String COMPANY_NAME = "companyName";
    private static final String USER_ID = "userId";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ClientService clientService = new ClientServiceImpl();

        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String emailAddress = request.getParameter(EMAIL_ADDRESS);
        String password = request.getParameter(PASSWORD);
        String companyName = request.getParameter(COMPANY_NAME);

        Client client = Client.newClientBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(emailAddress)
                .withPassword(MD5Util.encryptPassword(password))
                .withCompanyName(companyName)
                .build();
        Client clientWithInspector = clientService.assignInspector(client);
        Optional<Client> optionalClient = Optional.empty();

        try {
            optionalClient = clientService.register(clientWithInspector);

        } catch (WrongInputException e) {
            request.setAttribute("Registration error", e.getMessage());
            LOGGER.error(e.getMessage());
        }

        optionalClient.ifPresent(value -> request.getSession().setAttribute(USER_ID, value.getId()));

        return new GetMainClientPageCommand().execute(request, response);
    }
}
