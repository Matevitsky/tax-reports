package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.dto.ClientForAdmin;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.Report;
import com.matevitsky.repository.interfaces.ClientRepository;
import com.matevitsky.service.interfaces.ClientService;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.REPORT;

public class ClientServiceImpl implements ClientService {


    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final InspectorService inspectorService;

    private final ReportService reportService;


    public ClientServiceImpl(ClientRepository clientRepository, InspectorService inspectorService, ReportService reportService) {
        this.clientRepository = clientRepository;
        this.inspectorService = inspectorService;
        this.reportService = reportService;
    }

    @Override
    public Optional<Client> register(Client client) {
        try (Connection connection = ConnectorDB.getConnection()) {
            clientRepository.create(client, connection);
            return Optional.of(client);
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.findByEmail(email, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public boolean addReportToRequest(HttpServletRequest request, int reportId) {
        Optional<Report> reportById = reportService.getById(reportId);
        if (reportById.isPresent()) {
            request.setAttribute(REPORT, reportById.get());
            return true;
        }
        return false;
    }


    @Override
    public Optional<List<Client>> getClientsByInspectorId(int clientId) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.findClientsByInspectorId(clientId, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Client assignInspector(Client client) {

        Employee availableInspector = inspectorService.getFreeInspector();

        if (availableInspector == null) {
            LOGGER.warn("No available inspector");
            return client;
        }
        return Client.newClientBuilder()
                .withFirstName(client.getFirstName())
                .withLastName(client.getLastName())
                .withEmail(client.getEmail())
                .withPassword(client.getPassword())
                .withCompanyId(client.getCompanyId())
                .withInspectorId(availableInspector.getId())
                .build();
    }

    @Override
    public Optional<List<ClientForAdmin>> getAllClientsForInspector() {

        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.getAllClientsForAdmin(connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to Build clientForInspector with ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<ClientForAdmin>> getClientsForAdminByInspectorId(int inspectorId) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.getClientsForAdminByInspectorId(inspectorId, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to Build clientForInspector with ID " + e.getMessage());
        }
        return Optional.empty();
    }


   /* public Employee getFreeInspector() {
        InspectorService inspectorService = new InspectorServiceImpl();
        Optional<List<Employee>> optionalInspectorList = inspectorService.getAll();
        List<Employee> inspectorList;

        Map<Employee, Integer> map = new HashMap<>();
        if (optionalInspectorList.isPresent()) {
            inspectorList = optionalInspectorList.get();
            for (Employee inspector : inspectorList) {
                Optional<List<Client>> optionalClients = getClientsByInspectorId(inspector.getId());
                optionalClients.ifPresent(clients ->
                        map.put(inspector, clients.size()));
            }
        }
        return Collections.min(map.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();

    }
*/

    @Override
    public boolean create(Client client) {
        try (Connection connection = ConnectorDB.getConnection()) {
            clientRepository.create(client, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            clientRepository.deleteById(id, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to deleteById  entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public Client update(Client client) {
        try (Connection connection = ConnectorDB.getConnection()) {
            clientRepository.update(client, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return client;
    }

    @Override
    public Optional<Client> getById(int id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.getById(id, connection);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Client>> getAll() {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.getAll(connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to all entities" + e.getMessage());
        }
        return Optional.empty();
    }
}
