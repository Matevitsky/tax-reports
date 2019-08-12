package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Client;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.ClientRepository;
import com.matevitsky.repository.interfaces.InspectorRepository;
import com.matevitsky.service.interfaces.InspectorService;
import com.matevitsky.service.interfaces.ReportService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class InspectorServiceImpl implements InspectorService {

    private static final Logger LOGGER = Logger.getLogger(InspectorServiceImpl.class);

    private final InspectorRepository inspectorRepository;
    private final ClientRepository clientRepository;
    private final ReportService reportService;

    public InspectorServiceImpl(InspectorRepository inspectorRepository, ClientRepository clientRepository, ReportService reportService) {
        this.inspectorRepository = inspectorRepository;
        this.clientRepository = clientRepository;
        this.reportService = reportService;
    }

    @Override
    public boolean create(Employee inspector) {
        try (Connection connection = ConnectorDB.getConnection()) {
            inspectorRepository.create(inspector, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            inspectorRepository.deleteById(id, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to deleteById  entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public Employee update(Employee inspector) {

        try (Connection connection = ConnectorDB.getConnection()) {
            inspectorRepository.update(inspector, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return inspector;
    }

    @Override
    public Optional<Employee> getById(int id) {

        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Employee>> getAll() {

        try (Connection connection = ConnectorDB.getConnection()) {
            return inspectorRepository.getAll(connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to all entities" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
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
                    .filter(reportWithClientName ->
                            reportWithClientName.getReportStatus().equals(ReportStatus.NEW))
                    .collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    @Override
    public boolean acceptReport(int reportId) {

        Report update = null;

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
    public boolean declineReport(int reportId, String reasonToReject) {
        Report update = null;

        Optional<Report> optionalReport = reportService.getById(reportId);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            if (report.getStatus().equals(ReportStatus.ACCEPTED)) {
                LOGGER.info("Can't decline accepted report");
                return false;
            } else {
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
        }
        return update != null;
    }


    public Employee getFreeInspector() {

        List<Employee> inspectorList;

        Map<Employee, Integer> map = new HashMap<>();

        Optional<List<Employee>> optionalInspectorList = getAll();

        if (optionalInspectorList.isPresent()) {
            inspectorList = optionalInspectorList.get();
            for (Employee inspector : inspectorList) {
                try (Connection connection = ConnectorDB.getConnection()) {
                    Optional<List<Client>> optionalClients = clientRepository.findClientsByInspectorId(inspector.getId(), connection);
                    optionalClients.ifPresent(clients ->
                            map.put(inspector, clients.size()));
                } catch (SQLException s) {
                    LOGGER.warn("Failed to find client for inspectorId " + inspector.getId());
                }
            }
            if (map.isEmpty()) {
                Optional<Employee> optionalEmployee = getById(1);
                if (optionalEmployee.isPresent()) {
                    return optionalEmployee.get();
                }
            }

        }

        return Collections.min(map.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
