package com.matevitsky.service;

import com.matevitsky.entity.Inspector;

import java.util.Optional;

public interface InspectorService extends CrudService<Inspector> {

    Optional<Inspector> findByEmail(String email);
}
