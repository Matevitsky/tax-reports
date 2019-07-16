package com.matevitsky.controller.command;

import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;
import com.matevitsky.service.ClientService;
import com.matevitsky.service.ClientServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

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

        Client client = Client.newBuilder()
            .withFirstName(firstName)
            .withLastName(lastName)
            .withEmail(emailAddress)
            .withPassword(password)
            .withCompanyId(1)
            .build();

        try {
            clientService.register(client);
        } catch (WrongInputException e) {
            request.setAttribute("error", e.getMessage());
            LOGGER.error(e.getMessage());

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            //  return ERROR;
        }
        //TODO: Create Client Page
        return null;
    }
}
