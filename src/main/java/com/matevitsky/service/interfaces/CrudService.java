package com.matevitsky.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudService<S> {

    boolean create(S entity);

    boolean deleteById(Integer id);

    S update(S entity);

    Optional<S> getById(Integer id);

    Optional<List<S>> getAll();
}
