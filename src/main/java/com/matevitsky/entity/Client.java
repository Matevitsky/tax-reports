package com.matevitsky.entity;

import java.util.Objects;

public class Client extends User {
    private final String companyName;

    public Client(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password);
        this.companyName = builder.companyName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCompanyName() {
        return companyName;
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
        return companyName.equals(client.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName);
    }

    public static class Builder {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String companyName;

        private Builder() {
        }

        public Builder withId(Integer id) {
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

        public Builder withCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
