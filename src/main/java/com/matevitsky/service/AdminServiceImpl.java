package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Request;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;
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

    public AdminServiceImpl(RequestInspectorChangeRepository requestInspectorChangeRepository, ClientService clientService) {
        this.requestInspectorChangeRepository = requestInspectorChangeRepository;
        this.clientService = clientService;
    }

    @Override
    public void prepareAdminPage(HttpServletRequest request) {

        try (Connection connection = ConnectorDB.getConnection()) {
            Optional<List<Request>> optionalRequestList = requestInspectorChangeRepository.getAll(connection);
            List<Request> requestList = null;
            List<Client> clientList;
            if (optionalRequestList.isPresent()) {
                requestList = optionalRequestList.get();
            }
            clientList = requestList.stream().map(r -> clientService.getById(r.getClientId()).get()).collect(Collectors.toList());
            request.setAttribute("requestList", requestList);
            request.setAttribute("clientList", clientList);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
