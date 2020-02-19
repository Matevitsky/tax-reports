package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.ReportRepository;
import com.matevitsky.service.interfaces.ReportService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = Logger.getLogger(ReportServiceImpl.class);
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public boolean create(Report report) {
        try (Connection connection = ConnectorDB.getConnection()) {
            reportRepository.create(report, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            reportRepository.deleteById(id, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to deleteById  entity to database " + e.getMessage());
        }
        return false;
    }

    @Override
    public Report update(Report report) {
        try (Connection connection = ConnectorDB.getConnection()) {
            reportRepository.update(report, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return report;
    }

    @Override
    public Optional<Report> getById(int id) {

        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Report>> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<Report>> getReportsByClientId(int clientId) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.getByClientId(clientId, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<Report>> getClientActiveReports(int clientId) {
        Optional<List<Report>> optionalReportList = getReportsByClientId(clientId);
        List<Report> reports;
        if (optionalReportList.isPresent()) {
            reports = optionalReportList.get();
            optionalReportList.ifPresent(reports1 -> reports.removeIf(report -> report.getStatus().equals(ReportStatus.ACCEPTED)));
            return Optional.of(reports);
        }
        return Optional.empty();
    }

    @Override
    public Report changeStatusToInProgress(Report report) {
        Report reportInProgress = Report.newBuilder()
                .withId(report.getId())
                .withTittle(report.getTittle())
                .withContent(report.getContent())
                .withreasonToReject(report.getReasonToReject())
                .withStatus(ReportStatus.IN_PROGRESS)
                .withClientId(report.getClientId())
                .build();
        try (Connection connection = ConnectorDB.getConnection()) {
            reportRepository.update(reportInProgress, connection);
        } catch (SQLException s) {
            LOGGER.warn("Failed to change report status to IN_PROGRESS");
            return reportInProgress;
        }

        return reportInProgress;
    }
}