package com.matevitsky.service.interfaces;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Inspector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface InspectorService extends CrudService<Inspector> {

    Optional<Inspector> findByEmail(String email);

    Optional<List<ReportWithClientName>> getReports(int inspectorId);

    Optional<List<ReportWithClientName>> getNewReports(int inspectorId);

    boolean acceptReport(int reportId);

    void addNewReportsToRequest(HttpServletRequest request, int inspectorId);

    boolean declineReport(int reportId, String reasonToReject);
}
