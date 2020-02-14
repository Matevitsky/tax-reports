package com.matevitsky.repository.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<E> {
    /**
     * Create an object in DB and return true/false
     *
     * @param entity
     */
    void create(E entity, Connection connection) throws SQLException;

    /**
     * Delete an object by Id from Db  and return true/false
     *
     * @param id
     */
    void deleteById(int id, Connection connection) throws SQLException;

    /**
     * Update an object in DB and return updated object
     *
     * @param entity
     */
    void update(E entity, Connection connection) throws SQLException;

    /**
     * Get an object from DB by Id
     *
     * @param id
     * @return Optional
     */
    Optional<E> getById(int id, Connection connection) throws SQLException;

    /**
     * Ger all objects from DB
     *
     * @return List of objects
     */
    Optional<List<E>> getAll(Connection connection) throws SQLException;


}
