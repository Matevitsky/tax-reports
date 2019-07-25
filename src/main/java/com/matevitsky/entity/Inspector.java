package com.matevitsky.entity;

public class Inspector extends User {


    public Inspector(Builder builder) {
        super(builder.id, builder.firstName, builder.lastName, builder.email, builder.password, builder.role);

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Role role;


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


        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Inspector build() {
            return new Inspector(this);
        }
    }
}
