package com.matevitsky.dto;

import java.util.Objects;

public class UserForLogin {
    private final int id;
    private final String email;
    private final String password;
    private final Role role;

    public UserForLogin(int id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public enum Role {
        ADMIN,
        CLIENT,
        INSPECTOR
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserForLogin)) {
            return false;
        }
        UserForLogin that = (UserForLogin) o;
        return id == that.id &&
                email.equals(that.email) &&
                password.equals(that.password) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role);
    }
}
