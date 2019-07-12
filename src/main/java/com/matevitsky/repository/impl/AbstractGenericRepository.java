package com.matevitsky.repository.impl;

import com.matevitsky.repository.interfaces.GenericRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public abstract class AbstractGenericRepository<E> implements GenericRepository<E> {

    private static final Logger LOGGER = Logger.getLogger(AbstractGenericRepository.class);

    protected abstract String getCreateQuery(E entity);

    protected abstract String getDeleteByIdQuery(Integer id);

    protected abstract String getUpdateQuery(E entity);

    protected abstract String getByIdQuery(Integer id);

    protected abstract String getAllQuery();

    @Override
    public boolean create(E entity, Connection connection) {

        String sqlQuery = getCreateQuery(entity);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.warn("Creating entity failed, no rows affected.");
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());

        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id, Connection connection) {
        LOGGER.debug("method deleteById started for id " + id);

        String sqlQuery = getDeleteByIdQuery(id);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.error("deleteById entity failed for id " + id + " , no rows affected.");
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to deleteById  entity to database " + e.getMessage());
        }

        return false;
    }

    @Override
    public E update(E entity, Connection connection) {
        LOGGER.debug("method update started ");

        String sqlQuery = getUpdateQuery(entity);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.error("update entity failed , no rows affected.");
            } else {
                return entity;
            }

        } catch (SQLException e) {
            LOGGER.error("Failed to update  entity " + e.getMessage());
        }
        return entity;
    }

    @Override
    public Optional<E> getById(Integer id, Connection connection) {
        LOGGER.debug("method getById started for id " + id);

        String sqlQuery = getByIdQuery(id);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<E>> getAll(Connection connection) {
        LOGGER.debug("method getAll started ");

        String sqlQuery = getAllQuery();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToList(resultSet));

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();
    }

    protected abstract List<E> mapToList(ResultSet rs) throws SQLException;

    protected abstract E mapToObject(ResultSet rs) throws SQLException;

}
