package com.matevitsky.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<S> {

    boolean deleteById(Integer id);

    S update(S entity);

    Optional<S> getById(Integer id);

    Optional<List<S>> getAll();
}
