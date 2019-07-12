package com.matevitsky.entity;

import java.util.Objects;

public class Client extends User {
    private final int companyId;

    public Client(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password);
        this.companyId = builder.companyId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getCompanyName() {
        return companyId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyId=" + companyId +
                "} " + super.toString();
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
        return companyId == client.companyId;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), companyId);
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private int companyId;

        private Builder() {
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

        public Builder withCompanyName(int companyId) {
            this.companyId = companyId;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
