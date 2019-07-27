package com.matevitsky.entity;

import java.util.Objects;

public class Client extends User {
    private final String companyName;
    private final int inspectorId;


    public Client(ClientBuilder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password, builder.employeeRole);
        this.companyName = builder.companyName;
        this.inspectorId = builder.inspectorId;

    }

    public static ClientBuilder newClientBuilder() {
        return new ClientBuilder();
    }


    public String getCompanyName() {
        return companyName;
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
        return companyName == client.companyName &&
                inspectorId == client.inspectorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName, inspectorId);
    }

    public static class ClientBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private EmployeeRole employeeRole;
        private String companyName;
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


        public ClientBuilder withCompanyName(String companyName) {
            this.companyName = companyName;
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
