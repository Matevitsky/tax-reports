package com.matevitsky.service.interfaces;

import com.matevitsky.entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService extends CrudService<Report> {

    Optional<List<Report>> getReportsByClientId(int clientId);

    Optional<List<Report>> getClientActiveReports(int clientId);

    //TODO: перенести в другие сервисы

//    Optional<List<Report>> getInspectorNewReports(int inspectorId);

    Report changeStatusToInProgress(Report report);
}
