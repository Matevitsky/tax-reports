package com.matevitsky.entity;

import java.util.Objects;

public class Inspector extends User {

    private final int clientId;

    public Inspector(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password, builder.role);
        this.clientId = builder.clientId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inspector)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Inspector inspector = (Inspector) o;
        return clientId == inspector.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId);
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Role role;
        private int clientId;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
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

        public Builder withClientId(int clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Inspector build() {
            return new Inspector(this);
        }
    }
}
