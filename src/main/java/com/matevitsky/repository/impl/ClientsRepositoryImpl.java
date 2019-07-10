package com.matevitsky.repository.impl;

import com.matevitsky.entity.Client;
import com.matevitsky.repository.interfaces.ClientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ClientsRepositoryImpl extends AbstractGenericRepository<Client> implements ClientRepository {


    public static final String CREATE_QUERY = "INSERT INTO clients (FirstName, LastName, Email, Password, CompanyName) \n" +
            "VALUES ('%s', '%s', '%s', '%s','%s');";

//    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (Name, Email, Password, Role) VALUES  ('%s', '%s', '%s', '%s')";


    @Override
    public String getCreateQuery(Client client) {
        return String.format(CREATE_QUERY, client.getFirstName(), client.getLastName(), client.getEmail(), client.getPassword(), client.getCompanyName());
    }


    @Override
    public boolean deleteById(Integer id) {
        return false;
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
