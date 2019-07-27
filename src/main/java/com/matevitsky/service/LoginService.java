package com.matevitsky.service;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.Role;
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

        ClientService clientService = new ClientServiceImpl();
        InspectorService inspectorService = new InspectorServiceImpl();
        ReportService reportService = new ReportServiceImpl();
        Optional<User> inspectorByEmail = inspectorService.findByEmail(email);

        if (inspectorByEmail.isPresent()) {
            User inspector = inspectorByEmail.get();
            if (inspector.getRole().equals(Role.INSPECTOR)) {
                Optional<List<ReportWithClientName>> reports = inspectorService.getNewReports(inspector.getId());
                if (reports.isPresent()) {
                    request.setAttribute("reports", reports.get());
                }
            }
            return inspector;

        } else {
            Optional<Client> clientByEmail = clientService.findByEmail(email);
            if (clientByEmail.isPresent()) {
                Client client = clientByEmail.get();
                Optional<List<Report>> clientActiveReports = reportService.getClientActiveReports(client.getId());
                if (clientActiveReports.isPresent()) {
                    request.setAttribute("reports", clientActiveReports.get());
                }
                return client;
            }
        }
        //TODO: поменять имена в базе на единственное число

        return null;
    }
}
