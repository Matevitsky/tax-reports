package com.matevitsky.service;

import com.matevitsky.dto.UserForLogin;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;
import com.matevitsky.util.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginService {


    private ClientService clientService;
    private InspectorService inspectorService;
    private ReportService reportService;
    private AdminService adminService;

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public LoginService() {
        this.clientService = new ClientServiceImpl();
        this.inspectorService = new InspectorServiceImpl();
        this.reportService = new ReportServiceImpl();
        this.adminService = new AdminServiceImpl();
    }

    public UserForLogin login(String email, String password, HttpServletRequest request) {

        if (!Validation.emailAndPAsswordValidation(email, password)) {
            LOGGER.info("Email validation failed or password is empty");
            return null;
        }
        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();


        UserForLogin user = null;

        Optional<Employee> optionalInspector = inspectorService.findByEmail(email);

        if (optionalInspector.isPresent()) {
            Employee employee = optionalInspector.get();
            request.getSession().setAttribute("userId", employee.getId());
            switch (employee.getEmployeeRole()) {
                case INSPECTOR:
                    user = new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.INSPECTOR);
                    inspectorService.addNewReportsToRequest(request, employee.getId());
                    return user;
                //TODO: поменять название на Like prepare inspector page
                case ADMIN:
                    user = new UserForLogin(employee.getId(), employee.getEmail(), employee.getPassword(), UserForLogin.Role.ADMIN);
                    adminService.prepareAdminPage(request);
                    return user;
            }

        } else {
            Optional<Client> optionalClient = clientService.findByEmail(email);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                request.getSession().setAttribute("userId", client.getId());
                user = new UserForLogin(client.getId(), client.getEmail(), client.getPassword(), UserForLogin.Role.CLIENT);
            }
        }
        //TODO: поменять имена в базе на единственное число
        return user;
    }
}
