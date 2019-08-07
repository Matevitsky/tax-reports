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


    private ClientService clientService = new ClientServiceImpl();
    private InspectorService inspectorService = new InspectorServiceImpl();


    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

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
