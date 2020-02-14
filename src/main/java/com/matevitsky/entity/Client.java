package com.matevitsky.entity;

import java.util.Objects;

public class Client extends User {
    private final int companyId;
    private final int inspectorId;


    public Client(ClientBuilder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password, builder.employeeRole);
        this.companyId = builder.companyId;
        this.inspectorId = builder.inspectorId;

    }

    public static ClientBuilder newClientBuilder() {
        return new ClientBuilder();
    }


    public int getCompanyId() {
        return companyId;
    }

    public int getInspectorId() {
        return inspectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Client client = (Client) o;
        return companyId == client.companyId &&
                inspectorId == client.inspectorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyId, inspectorId);
    }

    public static class ClientBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private EmployeeRole employeeRole;
        private int companyId;
        private int inspectorId;


        private ClientBuilder() {
        }

        public static ClientBuilder newClientBuilder() {
            return new ClientBuilder();
        }

        public ClientBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ClientBuilder withFirstName(String name) {
            this.firstName = name;
            return this;
        }

        public ClientBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ClientBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder withPassword(String password) {
            this.password = password;
            return this;
        }


        public ClientBuilder withCompanyId(int companyId) {
            this.companyId = companyId;
            return this;
        }

        public ClientBuilder withInspectorId(int inspectorId) {
            this.inspectorId = inspectorId;
            return this;
        }

        public ClientBuilder withRole(EmployeeRole employeeRole) {
            this.employeeRole = employeeRole;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
