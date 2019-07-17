package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Report;
import com.matevitsky.repository.implementation.ReportRepositoryImpl;
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

    public ReportServiceImpl() {
        reportRepository = new ReportRepositoryImpl();
    }

    @Override
    public boolean create(Report report) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.create(report, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.deleteById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Report update(Report report) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.update(report, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return report;
    }

    @Override
    public Optional<Report> getById(Integer id) {

        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.getById(id, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Report>> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<Report>> getByClientId(int clientId) {
        try (Connection connection = ConnectorDB.getConnection()) {
            return reportRepository.getByClientId(clientId, connection);
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return Optional.empty();
    }
}
