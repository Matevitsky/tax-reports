package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Inspector;
import com.matevitsky.repository.implementation.InspectorRepositoryImpl;
import com.matevitsky.repository.interfaces.InspectorRepository;
import com.matevitsky.service.interfaces.InspectorService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InspectorServiceImpl implements InspectorService {

    private static final Logger LOGGER = Logger.getLogger(InspectorServiceImpl.class);
    private InspectorRepository inspectorRepository;

    public InspectorServiceImpl() {
        inspectorRepository = new InspectorRepositoryImpl();
    }

    @Override
    public boolean create(Inspector inspector) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.create(inspector, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.deleteById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Inspector update(Inspector inspector) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.update(inspector, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return inspector;
    }

    @Override
    public Optional<Inspector> getById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Inspector>> getAll() {

        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getAll(connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Inspector> findByEmail(String email) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.findByEmail(email, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }
}
