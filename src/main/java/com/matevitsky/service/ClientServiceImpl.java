package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;
import com.matevitsky.repository.impl.ClientsRepositoryImpl;
import com.matevitsky.repository.interfaces.ClientRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl() {
        clientRepository = new ClientsRepositoryImpl();
    }

    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public Client register(Client client) throws WrongInputException, SQLException {
        try (Connection connection = ConnectorDB.getConnection()) {
            clientRepository.create(client, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        //TODO: что вернуть?
        return client;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.deleteById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Client update(Client client) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.update(client, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return client;
    }
}
