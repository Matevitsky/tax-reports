package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.Request;
import com.matevitsky.repository.implementation.RequestInspectorChangeRepositoryImpl;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
    private RequestInspectorChangeRepository requestInspectorChangeRepository;
    private ClientService clientService;
    private InspectorService inspectorService;

    public AdminServiceImpl() {
        this.requestInspectorChangeRepository = new RequestInspectorChangeRepositoryImpl();
        this.clientService = new ClientServiceImpl();
        this.inspectorService = new InspectorServiceImpl();
    }

    @Override
    public void prepareAdminPage(HttpServletRequest request) {

        try (Connection connection = ConnectorDB.getConnection()) {
            Optional<List<Request>> optionalRequests = requestInspectorChangeRepository.getAll(connection);
            List<Request> requestList = null;
            List<Client> clientList;
            List<Employee> inspectorList = null;
            if (optionalRequests.isPresent()) {
                requestList = optionalRequests.get();
            }
            clientList = requestList.stream().map(r -> clientService.getById(r.getClientId()).get()).collect(Collectors.toList());
            Optional<List<Employee>> optionalInspectors = inspectorService.getAll();
            if (optionalInspectors.isPresent()) {
                inspectorList = optionalInspectors.get();
            }
            request.setAttribute("inspectorList", inspectorList);
            request.setAttribute("requestList", requestList);
            request.setAttribute("clientList", clientList);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
