package com.capgemini.wsb.fitnesstracker.user.api;
import jakarta.annotation.Nullable;


public record UserEmailDto(
        @Nullable Long id,
        String email
) {
}
