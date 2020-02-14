package com.matevitsky.entity;

import java.util.Objects;

public class Company {

    private final int id;

    private final String companyName;

    public Company(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName);
    }
}
