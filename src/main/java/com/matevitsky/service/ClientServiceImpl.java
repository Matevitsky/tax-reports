package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;
import com.matevitsky.repository.impl.ClientsRepositoryImpl;
import com.matevitsky.repository.interfaces.ClientRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl() {
        clientRepository = new ClientsRepositoryImpl();
    }

    @Override
    public Client register(Client client) throws WrongInputException, SQLException {
        Connection connection = ConnectorDB.getConnection();
        clientRepository.create(client, connection);
        return null;
    }
}
