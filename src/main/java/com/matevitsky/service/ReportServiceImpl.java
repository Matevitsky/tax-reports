package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Report;
import com.matevitsky.repository.implementation.ReportRepositoryImpl;
import com.matevitsky.repository.interfaces.ReportRepository;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Report update(Report entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Report> getById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<Report>> getAll() {
        throw new UnsupportedOperationException();
    }
}
