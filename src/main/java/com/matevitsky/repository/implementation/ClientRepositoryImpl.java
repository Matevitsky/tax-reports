package com.matevitsky.repository.implementation;

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
        "INSERT IGNORE INTO clients (first_name, last_name, email, password, company_name, employee_id)" +
            "VALUES ('%s', '%s', '%s', '%s', '%s','%d');";

    private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE client_id='%d'";
    private static final String UPDATE_CLIENT_SQL =
            "UPDATE clients SET first_name='%s', last_name='%s', email='%s', password='%s', company_name='%s'," +
                " employee_id='%d' where client_id='%d'";
    private static final String SELECT_CLIENT_BY_ID_SQL = "SELECT  * FROM clients WHERE client_id='%d'";
    private static final String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients";
    private static final String SELECT_CLIENT_BY_EMAIL_SQL = "SELECT * FROM clients  where email='%s'";
    private static final String SELECT_CLIENT_BY_INSPECTOR_ID_SQL = "SELECT * FROM clients  where employee_id='%d'";
    private static final String SELECT_CLIENT_REPORTS_SQL = "SELECT first_name,last_name,company_name,employee_id,report_id\n" +
            "FROM clients\n" +
            "INNER JOIN reports r ON clients.client_id = r.client_id\n" +
            "WHERE r.client_id = '%d'";



    @Override
    public String getCreateQuery(Client client) {
        return String.format(CREATE_CLIENT_SQL, client.getCompanyName(), client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getCompanyName(), client.getInspectorId());
    }

    @Override
    public String getDeleteByIdQuery(int id) {
        return String.format(DELETE_CLIENT_SQL, id);
    }

    @Override
    public String getUpdateQuery(Client client) {
        return String.format(UPDATE_CLIENT_SQL, client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getCompanyName(), client.getInspectorId(), client.getId());
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

            int id = resultSet.getInt(CLIENT_ID);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            String email = resultSet.getString(EMAIL);
            String password = resultSet.getString(PASSWORD);
            String companyName = resultSet.getString(COMPANY_NAME);

            int inspectorId = resultSet.getInt(EMPLOYEE_ID);
            client = Client.newClientBuilder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withCompanyName(companyName)
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
}
