package com.capgemini.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {

    @Override
    public String email() {
        return email;
    }

    @Override
    public LocalDate birthdate() {
        return birthdate;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    @Nullable
    public Long id() {
        return id;
    }


}