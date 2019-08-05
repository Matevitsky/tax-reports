package com.matevitsky.service;

import com.matevitsky.dto.UserForLogin;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.util.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Locale.ENGLISH;

public class LoginService {


    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    private static final String CLIENT = "client";
    private static final String ADMIN = "admin";
    private static final String INSPECTOR = "inspector";
    private static final String ADMIN_NAME = "adminName";
    private static final String CLIENT_NAME = "clientName";
    private static final String LOCALE = "locale";
    private ClientService clientService;
    private InspectorService inspectorService;

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public LoginService() {
        this.clientService = new ClientServiceImpl();
        this.inspectorService = new InspectorServiceImpl();

    }

    public UserForLogin login(String email, String password, HttpServletRequest request) {

        if (!Validation.emailAndPAsswordValidation(email, password)) {
            LOGGER.info("Email validation failed or password is empty");
            return null;
        }
        UserForLogin user = null;
        Optional<Employee> optionalInspector = inspectorService.findByEmail(email);
        request.getSession().setAttribute(LOCALE, ENGLISH);

        if (optionalInspector.isPresent()) {
            Employee employee = optionalInspector.get();
            request.getSession().setAttribute(USER_ID, employee.getId());
            request.getSession().setAttribute(ADMIN_NAME, employee.getFirstName() + " " + employee.getLastName());

            switch (employee.getEmployeeRole()) {
                case INSPECTOR:
                    request.getSession().setAttribute(ROLE, INSPECTOR);
                    user = new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.INSPECTOR);
                    return user;

                case ADMIN:
                    request.getSession().setAttribute(ROLE, ADMIN);
                    user = new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.ADMIN);
                    return user;
            }

        }

        Optional<Client> optionalClient = clientService.findByEmail(email);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            request.getSession().setAttribute(USER_ID, client.getId());
            request.getSession().setAttribute(ROLE, CLIENT);
            request.getSession().setAttribute(CLIENT_NAME, client.getFirstName() + " " + client.getLastName());
            Optional<Employee> inspector = clientService.getInspector(client.getId());
            inspector.ifPresent(employee -> request.getSession().setAttribute(INSPECTOR, employee));

            user = new UserForLogin(client.getId(), client.getEmail(), client.getPassword(), UserForLogin.Role.CLIENT);
        }

        return user;
    }
}
