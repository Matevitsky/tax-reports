package com.matevitsky.repository.impl;

import com.matevitsky.entity.Client;
import com.matevitsky.repository.interfaces.ClientRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ClientsRepositoryImpl extends AbstractGenericRepository<Client> implements ClientRepository {

    private static final Logger LOGGER = Logger.getLogger(ClientsRepositoryImpl.class);

    private static final String CREATE_SQL = "INSERT INTO clients (FirstName, LastName, Email, Password, CompanyName) \n" +
            "VALUES ('%s', '%s', '%s', '%s','%s');";

    private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE ID=%d";
    private static final String UPDATE_CLIENT_SQL = "UPDATE clients set FirstName='%s', LastName='%s', Email='%s', Password='%s', CompanyName='%s' where ID=%d";
    private static final String SELECT_CLIENT_BY_ID_SQL = "SELECT  * FROM clients WHERE ID=%d";



    @Override
    public String getCreateQuery(Client client) {
        return String.format(CREATE_SQL, client.getFirstName(), client.getLastName(), client.getEmail(), client.getPassword(), client.getCompanyName());
    }

    @Override
    public String getDeleteByIdQuery(Integer id) {
        return String.format(DELETE_CLIENT_SQL, id);
    }

    @Override
    public String getUpdateQuery(Client client) {
        return String.format(UPDATE_CLIENT_SQL, client.getFirstName(), client.getLastName(), client.getEmail(), client.getPassword(), client.getCompanyName(), client.getId());
    }

    @Override
    public String getByIdQuery(Integer id) {

        return String.format(SELECT_CLIENT_BY_ID_SQL, id);
    }


    @Override
    public Optional<Client> getAll(Connection connection) {
        return Optional.empty();
    }

    @Override
    protected List<Client> mapToList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Client mapToObject(ResultSet resultSet) {
        Client client = null;
        try {
            resultSet.next();
            Integer id = resultSet.getInt("ID");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String companyName = resultSet.getString("CompanyName");
            client = Client.newBuilder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withCompanyName(companyName)
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());

        }

        return client;
    }
}
