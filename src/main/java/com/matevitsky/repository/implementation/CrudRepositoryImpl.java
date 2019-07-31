package com.matevitsky.repository.implementation;

import com.matevitsky.repository.interfaces.CrudRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public abstract class CrudRepositoryImpl<E> implements CrudRepository<E> {

    private static final Logger LOGGER = Logger.getLogger(CrudRepositoryImpl.class);

    protected abstract String getCreateQuery(E entity);

    protected abstract String getDeleteByIdQuery(int id);

    protected abstract String getUpdateQuery(E entity);

    protected abstract String getByIdQuery(int id);

    protected abstract String getAllQuery();

    @Override
    public void create(E entity, Connection connection) throws SQLException {
        LOGGER.debug("method create started for entity " + entity.toString());

        String sqlQuery = getCreateQuery(entity);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.warn("Creating entity failed, no rows affected.");

            }
        }
    }

    @Override
    public void deleteById(int id, Connection connection) throws SQLException {
        LOGGER.debug("method deleteById started for id " + id);

        String sqlQuery = getDeleteByIdQuery(id);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.error("deleteById entity failed for id " + id + " , no rows affected.");
            }
        }
    }

    @Override
    public void update(E entity, Connection connection) throws SQLException {
        LOGGER.debug("method update started ");

        String sqlQuery = getUpdateQuery(entity);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (statement.executeUpdate() == 0) {
                LOGGER.error("update entity failed , no rows affected.");
            }
        }
    }

    @Override
    public Optional<E> getById(int id, Connection connection) throws SQLException {
        LOGGER.debug("method getById started for id " + id);

        String sqlQuery = getByIdQuery(id);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));
        }
    }

    @Override
    public Optional<List<E>> getAll(Connection connection) throws SQLException {
        LOGGER.debug("method getAll started ");

        String sqlQuery = getAllQuery();
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToList(resultSet));
        }
    }

    protected abstract List<E> mapToList(ResultSet resultSet);

    protected abstract E mapToObject(ResultSet resultSet);

}
