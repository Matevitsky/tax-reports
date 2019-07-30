package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Request;
import com.matevitsky.repository.implementation.RequestInspectorChangeRepositoryImpl;
import com.matevitsky.repository.interfaces.RequestInspectorChangeRepository;
import com.matevitsky.service.interfaces.RequestService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RequestServiceImpl implements RequestService {

    private static final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class);

    private RequestInspectorChangeRepository requestInspectorChangeRepository;

    public RequestServiceImpl() {
        this.requestInspectorChangeRepository = new RequestInspectorChangeRepositoryImpl();
    }

    @Override
    public boolean create(Request request) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return requestInspectorChangeRepository.create(request, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return requestInspectorChangeRepository.deleteById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Request update(Request entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Request> getById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return requestInspectorChangeRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Request>> getAll() {
        try (Connection connection = ConnectorDB.getConnection()) {
            return requestInspectorChangeRepository.getAll(connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }
}
