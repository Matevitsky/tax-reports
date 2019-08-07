package com.matevitsky.repository.implementation;

import com.matevitsky.dto.ClientForAdmin;
import com.matevitsky.entity.Client;
import com.matevitsky.repository.interfaces.ClientRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;


public class ClientRepositoryImpl extends CrudRepositoryImpl<Client> implements ClientRepository {

    private static final Logger LOGGER = Logger.getLogger(ClientRepositoryImpl.class);

    private static final String CREATE_CLIENT_SQL = "INSERT INTO companies(company_name) VALUES ('%s') ON DUPLICATE KEY UPDATE company_id = company_id + 0;" +
            "INSERT IGNORE INTO clients (client_first_name, client_last_name, client_email, password, company_name, employee_id)" +
            "VALUES ('%s', '%s', '%s', '%s', '%s','%d');";

    private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE client_id='%d'";
    private static final String UPDATE_CLIENT_SQL =
            "UPDATE clients SET client_first_name='%s', client_last_name='%s', client_email='%s', password='%s', company_id='%s'," +
                    " employee_id='%d' where client_id='%d'";
    private static final String SELECT_CLIENT_BY_ID_SQL = "SELECT  * FROM clients WHERE client_id='%d'";
    private static final String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients";

    private static final String SELECT_CLIENT_BY_EMAIL_SQL = "SELECT * FROM clients  where client_email='%s'";
    private static final String SELECT_CLIENT_BY_INSPECTOR_ID_SQL = "SELECT * FROM clients  where employee_id='%d'";
    private static final String SELECT_All_CLIENT_FOR_ADMIN_SQL = "select " +
            "client_id,client_first_name,client_last_name,client_email,company_name,employee_first_name,employee_last_name\n" +
            "from clients\n" +
            "join employees e on clients.employee_id = e.employee_id\n" +
            "join companies c on clients.company_id = c.company_id\n";
    private static final String SELECT_CLIENT_FOR_ADMIN_BY_INSPECTOR_ID_SQL = " select client_id,client_first_name,client_last_name,client_email,company_name,employee_first_name,employee_last_name\n" +
            "from clients\n" +
            "join employees e on clients.employee_id = e.employee_id\n" +
            "join companies c on clients.company_id = c.company_id\n" +
            "WHERE e.employee_id='%d'";


    @Override
    public String getCreateQuery(Client client) {
        return String.format(CREATE_CLIENT_SQL, client.getCompanyId(), client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getCompanyId(), client.getInspectorId());
    }

    @Override
    public String getDeleteByIdQuery(int id) {
        return String.format(DELETE_CLIENT_SQL, id);
    }

    @Override
    public String getUpdateQuery(Client client) {
        return String.format(UPDATE_CLIENT_SQL, client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getCompanyId(), client.getInspectorId(), client.getId());
    }

    @Override
    public String getByIdQuery(int id) {

        return String.format(SELECT_CLIENT_BY_ID_SQL, id);
    }

    @Override
    public String getAllQuery() {
        return SELECT_ALL_CLIENTS_SQL;
    }


    @Override
    protected List<Client> mapToList(ResultSet resultSet) {

        List<Client> allClientList = new ArrayList<>();
        Client client;

        try {
            while (resultSet.next()) {
                client = mapToObject(resultSet);
                allClientList.add(client);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty RowSet");
        }
        return allClientList;
    }

    @Override
    protected Client mapToObject(ResultSet resultSet) {

        Client client = null;

        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }

            int id = resultSet.getInt(DB_CLIENT_ID);
            String firstName = resultSet.getString(CLIENT_FIRST_NAME);
            String lastName = resultSet.getString(CLIENT_LAST_NAME);
            String email = resultSet.getString(CLIENT_EMAIL);
            String password = resultSet.getString(PASSWORD);
            int companyId = resultSet.getInt(COMPANY_ID);


            int inspectorId = resultSet.getInt(EMPLOYEE_ID);
            client = Client.newClientBuilder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withCompanyId(companyId)
                    .withInspectorId(inspectorId)
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return client;
    }

    @Override
    public Optional<Client> findByEmail(String email, Connection connection) throws SQLException {

        String sqlQuery = String.format(SELECT_CLIENT_BY_EMAIL_SQL, email);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));
        }
    }

    @Override
    public Optional<List<Client>> findClientsByInspectorId(int inspectorId, Connection connection) throws SQLException {

        String sqlQuery = String.format(SELECT_CLIENT_BY_INSPECTOR_ID_SQL, inspectorId);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToList(resultSet));
        }
    }

    @Override
    public Optional<List<ClientForAdmin>> getAllClientsForAdmin(Connection connection) {

        String sqlQuery = String.format(SELECT_All_CLIENT_FOR_ADMIN_SQL);

        List<ClientForAdmin> clientForAdminList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientForAdminList.add(mapToClientForAdmin(resultSet));
            }

        } catch (SQLException s) {
            LOGGER.warn("getAllClientsForAdmin failed " + s.getMessage());
        }

        return Optional.of(clientForAdminList);
    }

    private ClientForAdmin mapToClientForAdmin(ResultSet resultSet) throws SQLException {

        ClientForAdmin clientForAdmin;
        int id = resultSet.getInt(DB_CLIENT_ID);
        String clientFirstName = resultSet.getString(CLIENT_FIRST_NAME);
        String clientLastName = resultSet.getString(CLIENT_LAST_NAME);
        String email = resultSet.getString(CLIENT_EMAIL);
        String companyName = resultSet.getString(DB_COMPANY_NAME);
        String inspectorFirstName = resultSet.getString(DB_EMPLOYEE_FIRST_NAME);
        String inspectorLastName = resultSet.getString(DB_EMPLOYEE_LAST_NAME);

        clientForAdmin = new ClientForAdmin(Client.newClientBuilder()
                .withFirstName(clientFirstName)
                .withLastName(clientLastName)
                .withEmail(email)
                .withId(id), inspectorFirstName, inspectorLastName, companyName);
        return clientForAdmin;
    }

    @Override
    public Optional<List<ClientForAdmin>> getClientsForAdminByInspectorId(int inspectorId, Connection connection) {

        String sqlQuery = String.format(SELECT_CLIENT_FOR_ADMIN_BY_INSPECTOR_ID_SQL, inspectorId);

        List<ClientForAdmin> clientForAdminList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientForAdminList.add(mapToClientForAdmin(resultSet));
            }

        } catch (SQLException s) {
            LOGGER.warn("getAllClientsForAdmin failed " + s.getMessage());
        }

        return Optional.of(clientForAdminList);
    }

}
