package com.matevitsky.util;

public final class Validation {

    private Validation() {
    }

    /**
     * Validate the email
     *
     * @param email String
     * @return boolean
     */


    public static final boolean emailAndPAsswordValidation(String email, String password) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return email.matches(regex) || password.isEmpty();
    }
}
