package com.matevitsky.service;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Inspector;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.User;
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

    public User login(String email, String password, HttpServletRequest request) {

        if (!Validation.emailAndPAsswordValidation(email, password)) {
            LOGGER.info("Email validation failed or password is empty");
            return null;
        }

        User user = null;
        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();
        Optional<Inspector> inspector = inspectorService.findByEmail(email);

        if (inspector.isPresent()) {
            user = inspector.get();

        } else {
            Optional<Client> client = clientService.findByEmail(email);
            if (client.isPresent()) {
                user = client.get();
                ReportService reportService = new ReportServiceImpl();
                Optional<List<Report>> clientActiveReports = reportService.getClientActiveReports(user.getId());
                if (clientActiveReports.isPresent()) {
                    request.setAttribute("reports", clientActiveReports.get());
                }
            }
        }
        //TODO: поменять имена в базе на единственное число

        return user;
    }
}
