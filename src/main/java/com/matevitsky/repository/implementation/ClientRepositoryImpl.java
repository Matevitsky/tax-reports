package com.matevitsky.repository.implementation;

import com.matevitsky.entity.Client;
import com.matevitsky.entity.Role;
import com.matevitsky.repository.interfaces.ClientRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClientRepositoryImpl extends CrudRepositoryImpl<Client> implements ClientRepository {

    private static final Logger LOGGER = Logger.getLogger(ClientRepositoryImpl.class);

    private static final String CREATE_CLIENT_SQL = "INSERT INTO clients (first_name, last_name, email, password," +
            " role, company_id, inspector_id) VALUES ('%s', '%s', '%s', '%s', '%s','%d','%d');";

    private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE client_id=%d";
    private static final String UPDATE_CLIENT_SQL =
            "UPDATE clients SET first_name='%s', last_name='%s', email='%s', password='%s', role='%s', company_id='%d'," +
                    " inspector_id='%d' where client_id=%d";
    private static final String SELECT_CLIENT_BY_ID_SQL = "SELECT  * FROM clients WHERE client_id=%d";
    private static final String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients";
    private static final String SELECT_CLIENT_BY_EMAIL_SQL = "SELECT * FROM clients  where email='%s'";


    @Override
    public String getCreateQuery(Client client) {
        return String.format(CREATE_CLIENT_SQL, client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getRole(), client.getCompanyName());
    }

    @Override
    public String getDeleteByIdQuery(int id) {
        return String.format(DELETE_CLIENT_SQL, id);
    }

    @Override
    public String getUpdateQuery(Client client) {
        return String.format(UPDATE_CLIENT_SQL, client.getFirstName(), client.getLastName(), client.getEmail(),
                client.getPassword(), client.getRole(), client.getCompanyName(), client.getId());
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
            if (!resultSet.first()) {
                resultSet.next();
            }
            int id = resultSet.getInt("client_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            int companyName = resultSet.getInt("company_id");
            int inspectorId = resultSet.getInt("inspector_id");
            client = Client.newBuilder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withRole(Role.valueOf(role))
                    .withCompanyId(companyName)
                    .withInspectorId(inspectorId)
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());

        }

        return client;
    }

    @Override
    public Optional<Client> findByEmail(String email, Connection connection) {
        String sqlQuery = String.format(SELECT_CLIENT_BY_EMAIL_SQL, email);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();

    }
}
