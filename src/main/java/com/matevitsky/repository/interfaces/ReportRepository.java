package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Report;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report> {
    Optional<List<Report>> getByClientId(int clientId, Connection connection);


}
