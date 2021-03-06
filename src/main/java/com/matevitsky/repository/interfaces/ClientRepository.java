package com.matevitsky.repository.interfaces;

import com.matevitsky.dto.ClientForAdmin;
import com.matevitsky.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client> {

    Optional<Client> findByEmail(String email, Connection connection) throws SQLException;

    Optional<List<Client>> findClientsByInspectorId(int inspectorId, Connection connection);

    Optional<List<ClientForAdmin>> getAllClientsForAdmin(Connection connection);

    Optional<List<ClientForAdmin>> getClientsForAdminByInspectorId(int inspectorId, Connection connection);
}
