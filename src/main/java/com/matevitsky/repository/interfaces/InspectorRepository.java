package com.matevitsky.repository.interfaces;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Inspector;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface InspectorRepository extends CrudRepository<Inspector> {

    Optional<Inspector> findByEmail(String email, Connection connection);

    Optional<List<ReportWithClientName>> getAllClientReports(int inspectorId, Connection connection);
}
