package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Client;
import com.matevitsky.exception.WrongInputException;
import com.matevitsky.repository.implementation.ClientRepositoryImpl;
import com.matevitsky.repository.interfaces.ClientRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        clientRepository = new ClientRepositoryImpl();
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
    public Optional<Client> findByEmail(String email) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.findByEmail(email, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();

    }

    @Override
    public boolean create(Client client) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return clientRepository.create(client, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
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

    @Override
    public Optional<Client> getById(Integer id) {
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
            LOGGER.error(e);
        }
        return Optional.empty();
    }
}
