package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Client;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client> {

    Optional<Client> findByEmail(String email, Connection connection);

    Optional<List<Client>> findClientsByInspectorId(int inspectorId, Connection connection);
}
