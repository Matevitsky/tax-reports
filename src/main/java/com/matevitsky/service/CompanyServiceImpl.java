package com.matevitsky.service;

import com.matevitsky.db.ConnectorDB;
import com.matevitsky.entity.Company;
import com.matevitsky.repository.interfaces.CompanyRepository;
import com.matevitsky.service.interfaces.CompanyService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {

    private static final Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean create(Company company) {
        try (Connection connection = ConnectorDB.getConnection()) {
            companyRepository.create(company, connection);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Failed to add entity to database " + e.getMessage());
        }
        return false;

    }

    @Override
    public boolean deleteById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Company update(Company entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Company> getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<Company>> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Company> getByCompanyByName(String companyName) {

        try (Connection connection = ConnectorDB.getConnection()) {
            return companyRepository.getByCompanyName(companyName, connection);
        } catch (SQLException e) {
            LOGGER.error("Failed getByCompanyByName  " + e.getMessage());
        }

        return Optional.empty();
    }
}
