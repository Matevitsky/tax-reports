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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.matevitsky.controller.constant.ParameterConstant.CLIENT_LIST;

public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);


    private final RequestInspectorChangeRepository requestInspectorChangeRepository = new RequestInspectorChangeRepositoryImpl();
    private final RequestService requestService = new RequestServiceImpl();
    private final ClientRepository clientRepository = new ClientRepositoryImpl();
    private final InspectorService inspectorService = new InspectorServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();


    @Override
    public void prepareAdminPage(HttpServletRequest request) {

        Optional<List<Request>> optionalRequests = requestService.getAll();
        List<Request> requestList = null;
        List<Client> clientList;

        if (optionalRequests.isPresent()) {
            requestList = optionalRequests.get();
        }

        clientList = Objects.requireNonNull(requestList)
                .stream().map(r -> clientService.getById(r.getClientId()).get()).collect(Collectors.toList());
        request.setAttribute(CLIENT_LIST, clientList);

        Optional<List<Employee>> optionalInspectors = inspectorService.getAll();
        optionalInspectors.ifPresent(employees -> request.setAttribute("inspectorList", optionalInspectors.get()));

        addRequestAmountToHeader(request);
    }

    @Override
    public void addRequestAmountToHeader(HttpServletRequest request) {
        Optional<List<Request>> optionalRequestList = requestService.getAll();
        optionalRequestList.ifPresent(requests -> request.setAttribute("requestList", requests));
    }


    @Override
    public boolean assignInspector(int clientId, int inspectorId) {
        Connection connection = null;
        try {
            connection = ConnectorDB.getConnection();
            Client client;
            Optional<Client> optionalClient = clientService.getById(clientId);
            if (optionalClient.isPresent()) {
                client = optionalClient.get();
                Client assignedClient = Client.newClientBuilder()
                        .withInspectorId(inspectorId)
                        .withFirstName(client.getFirstName())
                        .withLastName(client.getLastName())
                        .withCompanyId(client.getCompanyId())
                        .withEmail(client.getEmail())
                        .withPassword(client.getPassword())
                        .withId(clientId)
                        .build();
                connection.setAutoCommit(false);
                clientRepository.update(assignedClient, connection);
                requestInspectorChangeRepository.deleteByClientID(clientId, connection);

                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback();
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
