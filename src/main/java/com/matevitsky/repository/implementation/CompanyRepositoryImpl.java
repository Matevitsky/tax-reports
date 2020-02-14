package com.matevitsky.repository.implementation;

import com.matevitsky.entity.Company;
import com.matevitsky.repository.interfaces.CompanyRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.DB_COMPANY_ID;
import static com.matevitsky.controller.constant.ParameterConstant.DB_COMPANY_NAME;

public class CompanyRepositoryImpl extends CrudRepositoryImpl<Company> implements CompanyRepository {


    private static final Logger LOGGER = Logger.getLogger(CompanyRepositoryImpl.class);

    private static final String CREATE_COMPANY = "INSERT INTO companies (company_id, company_name) \n" +
            "VALUES ('%d','%s');";
    private static final String SELECT_COMPANY_BY_NAME = "SELECT * FROM companies WHERE company_name='%s'";


    @Override
    protected String getCreateQuery(Company company) {

        return String.format(CREATE_COMPANY, company.getId(), company.getCompanyName());
    }

    @Override
    protected String getDeleteByIdQuery(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getUpdateQuery(Company entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getByIdQuery(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getAllQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected List<Company> mapToList(ResultSet resultSet) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Company mapToObject(ResultSet resultSet) {
        Company company = null;
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            int id = resultSet.getInt(DB_COMPANY_ID);
            String companyName = resultSet.getString(DB_COMPANY_NAME);
            company = new Company(id, companyName);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return company;
    }

    @Override
    public Optional<Company> getByCompanyName(String companyName, Connection connection) throws SQLException {
        String sqlQuery = String.format(SELECT_COMPANY_BY_NAME, companyName);
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));
        }
    }
}
