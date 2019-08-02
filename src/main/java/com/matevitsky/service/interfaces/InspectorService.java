package com.matevitsky.service.interfaces;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface InspectorService extends CrudService<Employee> {

    Optional<Employee> findByEmail(String email);

    Optional<List<ReportWithClientName>> getReports(int inspectorId);

    Optional<List<ReportWithClientName>> getNewReports(int inspectorId);

    boolean acceptReport(int reportId);

    void addDataToRequest(HttpServletRequest request, int inspectorId);

    boolean declineReport(int reportId, String reasonToReject);

}
