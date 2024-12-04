package com.capgemini.wsb.fitnesstracker.user.api;

public class UserAlreadyExistException extends RuntimeException {

    private UserAlreadyExistException(String message) {

        super(message);

    }

    public static class UserAlreadyExistIdException extends UserAlreadyExistException {
        public UserAlreadyExistIdException(Long id) {
            super("User with " + id + " already exist");
        }
    }

    public static class UserAlreadyExistEmailException extends UserAlreadyExistException {
        public UserAlreadyExistEmailException(String email) {
            super("User with email " + email + " already exist");
        }
    }
}