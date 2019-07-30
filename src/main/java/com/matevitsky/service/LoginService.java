package com.matevitsky.service;

import com.matevitsky.dto.UserForLogin;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.Report;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;
import com.matevitsky.util.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class LoginService {

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public UserForLogin login(String email, String password, HttpServletRequest request) {

        if (!Validation.emailAndPAsswordValidation(email, password)) {
            LOGGER.info("Email validation failed or password is empty");
            return null;
        }
        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();
        ReportService reportService = new ReportServiceImpl();

        UserForLogin user = null;

        Optional<Employee> optionalInspector = inspectorService.findByEmail(email);

        if (optionalInspector.isPresent()) {
            Employee employee = optionalInspector.get();
            switch (employee.getEmployeeRole()) {
                case INSPECTOR:
                    user = new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.INSPECTOR);
                    inspectorService.addNewReportsToRequest(request, employee.getId());
                    //TODO: поменять название на Like  prepare inspector page
                case ADMIN:
                    user = new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.ADMIN);
                    adminService.prepareAdminPage(request);
            }

        } else {
            Optional<Client> optionalClient = clientService.findByEmail(email);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                user = new UserForLogin(client.getId(), client.getEmail(), client.getPassword(), UserForLogin.Role.CLIENT);
                Optional<List<Report>> clientActiveReports = reportService.getClientActiveReports(client.getId());
                if (clientActiveReports.isPresent()) {
                    request.setAttribute("reports", clientActiveReports.get());
                }
            }
        }
        //TODO: поменять имена в базе на единственное число
        return user;
    }
}
