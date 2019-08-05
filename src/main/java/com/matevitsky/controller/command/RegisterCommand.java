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
                .withPassword(MD5Util.encryptPassword(password))
                .withCompanyName(companyName)
                .build();
        Client clientWithInspector = clientService.assignInspector(client);
        Optional<Client> optionalClient = Optional.empty();

        try {
            optionalClient = clientService.register(clientWithInspector);

        } catch (WrongInputException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error(e.getMessage());
        }

        if (optionalClient.isPresent()) {
            request.getSession().setAttribute("userId", optionalClient.get().getId());
        }
        //TODO: Create Client Page подправить чтобы было красиво

        return new GetMainClientPageCommand().execute(request, response);
    }
}
