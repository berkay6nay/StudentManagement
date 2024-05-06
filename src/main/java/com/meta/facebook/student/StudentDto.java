package com.meta.facebook.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "First name shall not be empty!!!")
        String firstName,
        @NotEmpty(message = "Last name shall not be empty!!!")
        String lastName,
        String email,
        Integer schoolId
) {
}
