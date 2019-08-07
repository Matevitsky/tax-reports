package com.matevitsky.entity;

public class Employee extends User {

    private final EmployeeRole employeeRole;

    public Employee(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password, builder.employeeRole);
        this.employeeRole = builder.employeeRole;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }


    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private EmployeeRole employeeRole;


        private Builder() {

        }

        public static Builder Builder() {
            return new Builder();
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String name) {
            this.firstName = name;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmployeeRole(EmployeeRole employeeRole) {
            this.employeeRole = employeeRole;
            return this;
        }


        public Employee build() {
            return new Employee(this);
        }
    }
}
