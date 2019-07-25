package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Inspector;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.implementation.ClientRepositoryImpl;
import com.matevitsky.repository.implementation.InspectorRepositoryImpl;
import com.matevitsky.repository.interfaces.ClientRepository;
import com.matevitsky.repository.interfaces.InspectorRepository;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InspectorServiceImpl implements InspectorService {

    private static final Logger LOGGER = Logger.getLogger(InspectorServiceImpl.class);
    private InspectorRepository inspectorRepository;
    private ClientRepository clientRepository;
    private ReportService reportService;

    public InspectorServiceImpl() {
        inspectorRepository = new InspectorRepositoryImpl();
        clientRepository = new ClientRepositoryImpl();
        reportService = new ReportServiceImpl();
    }

    @Override
    public boolean create(Inspector inspector) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.create(inspector, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.deleteById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Inspector update(Inspector inspector) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.update(inspector, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return inspector;
    }

    @Override
    public Optional<Inspector> getById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Inspector>> getAll() {

        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getAll(connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Inspector> findByEmail(String email) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.findByEmail(email, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<ReportWithClientName>> getReports(int inspectorId) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getAllClientReports(inspectorId, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<ReportWithClientName>> getNewReports(int inspectorId) {
        List<ReportWithClientName> reportList;
        Optional<List<ReportWithClientName>> allReports = getReports(inspectorId);
        if (allReports.isPresent()) {
            reportList = allReports.get();
            return Optional.of(reportList.stream()
                    .filter(reportWithClientName -> reportWithClientName.getReportStatus().equals(ReportStatus.NEW))
                    .collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    @Override
    public boolean acceptReport(int reportId) {

        Report update = null;
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            Report acceptedReport = Report.newBuilder()
                    .withClientId(report.getClientId())
                    .withContent(report.getContent())
                    .withStatus(ReportStatus.ACCEPTED)
                    .withId(reportId)
                    .withTittle(report.getTittle())
                    .build();

            update = reportService.update(acceptedReport);
        }
        return update != null;
    }

    @Override
    public void addNewReportsToRequest(HttpServletRequest request, int inspectorId) {
        Optional<Inspector> inspectorById = getById(inspectorId);
        if (inspectorById.isPresent()) {
            Inspector inspector = inspectorById.get();
            Optional<List<ReportWithClientName>> reports = getNewReports(inspector.getId());
            if (reports.isPresent()) {
                request.setAttribute("reports", reports.get());
            }
        }
    }

    @Override
    public boolean declineReport(int reportId, String reasonToReject) {
        Report update = null;
        ReportService reportService = new ReportServiceImpl();
        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            Report acceptedReport = Report.newBuilder()
                    .withClientId(report.getClientId())
                    .withContent(report.getContent())
                    .withStatus(ReportStatus.DECLINED)
                    .withId(reportId)
                    .withreasonToReject(reasonToReject)
                    .withTittle(report.getTittle())
                    .build();
            update = reportService.update(acceptedReport);
        }
        return update != null;
    }

}
