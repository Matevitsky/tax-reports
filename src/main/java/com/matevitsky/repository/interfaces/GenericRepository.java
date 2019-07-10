package com.matevitsky.repository.interfaces;

import java.sql.Connection;
import java.util.Optional;

public interface GenericRepository<E> {
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
    boolean deleteById(Integer id);

    /**
     * Update an object in DB and return updated object
     *
     * @param entity
     * @return
     */
    E update(E entity);

    /**
     * Get an object from DB by Id
     *
     * @param id
     * @return Optional
     */
    Optional<E> getById(Integer id);

    /**
     * Ger all objects from DB
     *
     * @return List of objects
     */
    Optional<E> getAll();
}
