package com.matevitsky.repository.interfaces;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<E> {
    /**
     * Create an object in DB and return true/false
     *
     * @param entity
     * @return boolean
     */
    boolean create(E entity, Connection connection);

    /**
     * Delete an object by Id from Db  and return true/false
     *
     * @param id
     * @return
     */
    boolean deleteById(int id, Connection connection);

    /**
     * Update an object in DB and return updated object
     *
     * @param entity
     * @return
     */
    E update(E entity, Connection connection);

    /**
     * Get an object from DB by Id
     *
     * @param id
     * @return Optional
     */
    Optional<E> getById(int id, Connection connection);

    /**
     * Ger all objects from DB
     *
     * @return List of objects
     */
    Optional<List<E>> getAll(Connection connection);


}
