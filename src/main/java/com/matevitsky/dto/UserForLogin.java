package com.matevitsky.dto;

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
}
