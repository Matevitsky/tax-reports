package com.matevitsky.service.interfaces;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface InspectorService extends CrudService<Employee> {

    Optional<Employee> findByEmail(String email);

    Optional<List<ReportWithClientName>> getReports(int inspectorId);

    Optional<List<ReportWithClientName>> getNewReports(int inspectorId);

    boolean acceptReport(int reportId);

    boolean declineReport(int reportId, String reasonToReject);

    Employee getFreeInspector();

}
