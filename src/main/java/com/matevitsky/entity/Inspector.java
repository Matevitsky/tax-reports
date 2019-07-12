package com.matevitsky.entity;

import java.util.Objects;

public class Inspector extends User {

    private final int clientId;

    public Inspector(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password);
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
        private int clientId;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(int id) {
            this.id = id;
            return this;
        }

        public Builder withLastName(int id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(int id) {
            this.id = id;
            return this;
        }

        public Builder withPassword(int id) {
            this.id = id;
            return this;
        }

        public Builder withClientId(int id) {
            this.id = id;
            return this;
        }
    }
}