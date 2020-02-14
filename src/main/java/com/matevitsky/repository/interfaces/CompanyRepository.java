package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company> {

    Optional<Company> getByCompanyName(String companyName, Connection connection) throws SQLException;

}
