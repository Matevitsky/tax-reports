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

import static com.matevitsky.controller.constant.ParameterConstant.*;
import static java.util.Locale.ENGLISH;

public class LoginService {

    private final ClientService clientService;
    private final InspectorService inspectorService;

    public LoginService(ClientService clientService, InspectorService inspectorService) {
        this.clientService = clientService;
        this.inspectorService = inspectorService;
    }

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public UserForLogin login(String email, String password, HttpServletRequest request) {

        if (!Validation.emailAndPAsswordValidation(email, password)) {
            LOGGER.info("Email validation failed or password is empty");
            return null;
        }

        Optional<Employee> optionalInspector = inspectorService.findByEmail(email);
        request.getSession().setAttribute(LOCALE, ENGLISH);

        if (optionalInspector.isPresent()) {
            return buildEmployee(optionalInspector.get(), request);
        }

        Optional<Client> optionalClient = clientService.findByEmail(email);

        return optionalClient.map(client -> buildClient(client, request)).orElse(null);

    }

    private UserForLogin buildEmployee(Employee employee, HttpServletRequest request) {

        request.getSession().setAttribute(USER_ID, employee.getId());
        request.getSession().setAttribute(ADMIN_NAME, employee.getFirstName() + " " + employee.getLastName());

        switch (employee.getEmployeeRole()) {
            case INSPECTOR:
                request.getSession().setAttribute(ROLE, INSPECTOR);
                return new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.INSPECTOR);


            case ADMIN:
                request.getSession().setAttribute(ROLE, ADMIN);
                return new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.ADMIN);

        }
        return null;

    }

    private UserForLogin buildClient(Client client, HttpServletRequest request) {

        request.getSession().setAttribute(USER_ID, client.getId());
        request.getSession().setAttribute(ROLE, CLIENT);
        request.getSession().setAttribute(CLIENT_NAME, client.getFirstName() + " " + client.getLastName());
        Optional<Employee> inspector = inspectorService.getById(client.getId());
        inspector.ifPresent(employee -> request.getSession().setAttribute(INSPECTOR, employee));

        return new UserForLogin(client.getId(), client.getEmail(), client.getPassword(), UserForLogin.Role.CLIENT);
    }
}
