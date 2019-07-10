package com.matevitsky.exception;

/**
 * Custom Exception for handle WrongInputExceptions
 * Throws when user input wrong data in fields
 */
public class WrongInputException extends Exception {
    /**
     * Constructor with String message of exception
     *
     * @param s - message of exception
     */
    public WrongInputException(String s) {
        super(s);
    }
}
