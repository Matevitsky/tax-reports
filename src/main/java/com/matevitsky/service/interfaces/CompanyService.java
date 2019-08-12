package com.matevitsky.service.interfaces;

import com.matevitsky.entity.Company;

import java.util.Optional;

public interface CompanyService extends CrudService<Company> {

    Optional<Company> getByCompanyByName(String companyName);
}
