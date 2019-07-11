package com.matevitsky.repository.impl;

import com.matevitsky.entity.Client;
import com.matevitsky.repository.interfaces.ClientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ClientsRepositoryImpl extends AbstractGenericRepository<Client> implements ClientRepository {


    private static final String CREATE_SQL = "INSERT INTO clients (FirstName, LastName, Email, Password, CompanyName) \n" +
            "VALUES ('%s', '%s', '%s', '%s','%s');";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE ID=%d";



    @Override
    public String getCreateQuery(Client client) {
        return String.format(CREATE_SQL, client.getFirstName(), client.getLastName(), client.getEmail(), client.getPassword(), client.getCompanyName());
    }

    @Override
    public String getDeleteByIdQuery(Integer id) {
        return String.format(DELETE_CLIENT_SQL, id);
    }

    @Override
    public Client update(Client entity) {
        return null;
    }

    @Override
    public Optional<Client> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> getAll() {
        return Optional.empty();
    }

    @Override
    protected List<Client> mapToList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected Client mapToObject(ResultSet rs) throws SQLException {
        return null;
    }
}
