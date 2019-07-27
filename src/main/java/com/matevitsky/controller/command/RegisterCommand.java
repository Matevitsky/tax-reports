package com.matevitsky.controller.command;


import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;
import com.matevitsky.service.ClientServiceImpl;
import com.matevitsky.service.interfaces.ClientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {


    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOGGER.debug("RegisterCommand Started");


        ClientService clientService = new ClientServiceImpl();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String emailAddress = request.getParameter("emailAddress");
        String password = request.getParameter("password");
        String companyName = request.getParameter("companyName");

        Client client = Client.newClientBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(emailAddress)
                .withPassword(password)
                .withCompanyName(companyName)
                .build();


        try {

            //TODO: подумать какого инспектора присвоить
            clientService.register(client);
        } catch (WrongInputException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error(e.getMessage());

        }
        //TODO: Create Client Page
        return null;
    }
}
