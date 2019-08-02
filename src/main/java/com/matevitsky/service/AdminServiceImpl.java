package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.Request;
import com.matevitsky.repository.implementation.ClientRepositoryImpl;
import com.matevitsky.repository.implementation.RequestInspectorChangeRepositoryImpl;
import com.matevitsky.repository.interfaces.ClientRepository;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import com.matevitsky.service.interfaces.AdminService;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.RequestService;
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
    private RequestService requestService;
    private ClientRepository clientRepository;
    private InspectorService inspectorService;
    private ClientService clientService;

    public AdminServiceImpl() {
        this.requestInspectorChangeRepository = new RequestInspectorChangeRepositoryImpl();
        this.clientRepository = new ClientRepositoryImpl();
        this.inspectorService = new InspectorServiceImpl();
        this.clientService = new ClientServiceImpl();
        this.requestService = new RequestServiceImpl();
    }

    @Override
    public void prepareAdminPage(HttpServletRequest request) {
        Optional<List<Request>> optionalRequests = requestService.getAll();
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
        request.setAttribute("clientList", clientList);
        addHeaderDataToRequest(request);


    }

    @Override
    public void addHeaderDataToRequest(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        Optional<List<Request>> optionalRequestList = requestService.getAll();
        if (optionalRequestList.isPresent()) {
            request.setAttribute("requestList", optionalRequestList.get());
        }
    }


    private String getAdminName(int id) {
        InspectorService inspectorService = new InspectorServiceImpl();
        Optional<Employee> optionalAdmin = inspectorService.getById(id);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get().getFirstName() + " " + optionalAdmin.get().getLastName();
        }
        return "";
    }

    @Override
    public boolean assignInspector(int clientId, int inspectorId) {
        Connection connection = null;
        try {
            connection = ConnectorDB.getConnection();
            Client client = null;
            Optional<Client> optionalClient = clientService.getById(clientId);
            if (optionalClient.isPresent()) {
                client = optionalClient.get();
            }
            Client assignedClient = Client.newClientBuilder()
                    .withInspectorId(inspectorId)
                    .withFirstName(client.getFirstName())
                    .withLastName(client.getLastName())
                    .withCompanyName(client.getCompanyName())
                    .withEmail(client.getEmail())
                    .withPassword(client.getPassword())
                    .withId(clientId)
                    .build();
            connection.setAutoCommit(false);
            clientRepository.update(assignedClient, connection);
            requestInspectorChangeRepository.deleteByClientID(clientId, connection);

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                connection.rollback();
                LOGGER.error("Transaction failed " + e.getMessage());
                return false;
            } catch (SQLException s) {
                LOGGER.error("Rollback failed " + e.getMessage());
                return false;
            }
        }
        return true;

    }


}
