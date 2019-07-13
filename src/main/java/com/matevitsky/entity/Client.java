package com.matevitsky.entity;

import java.util.Objects;

public class Client extends User {
    private final int companyId;
    private final int inspectorId;

    public Client(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password);
        this.companyId = builder.companyId;
        this.inspectorId = builder.inspectorId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getCompanyName() {
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

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private int companyId;
        private int inspectorId;

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

        public Builder withCompanyId(int companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder withInspectorId(int inspectorId) {
            this.inspectorId = inspectorId;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
