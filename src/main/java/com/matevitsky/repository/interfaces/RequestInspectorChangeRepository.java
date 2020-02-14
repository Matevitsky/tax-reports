package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Request;

import java.sql.Connection;
import java.sql.SQLException;

public interface RequestInspectorChangeRepository extends CrudRepository<Request> {

    void deleteByClientID(int clientId, Connection connection) throws SQLException;
}
