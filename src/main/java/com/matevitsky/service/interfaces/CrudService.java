package com.matevitsky.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudService<S> {

    boolean create(S entity);

    boolean deleteById(int id);

    S update(S entity);

    Optional<S> getById(int id);

    Optional<List<S>> getAll();
}
