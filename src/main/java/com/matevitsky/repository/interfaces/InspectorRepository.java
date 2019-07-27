package com.matevitsky.repository.interfaces;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface InspectorRepository extends CrudRepository<User> {

    Optional<User> findByEmail(String email, Connection connection);

    Optional<List<ReportWithClientName>> getAllClientReports(int inspectorId, Connection connection);
}
