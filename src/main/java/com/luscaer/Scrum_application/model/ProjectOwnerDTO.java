package com.luscaer.Scrum_application.model;

import jakarta.validation.constraints.*;

public record ProjectOwnerDTO(
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone cannot be blank")
        @Pattern(regexp = "^\\d{2}9\\d{8}$", message = "Phone number format is invalid")
        String phone,

        @NotBlank(message = "Gender cannot be blank")
        String gender,

        @NotBlank(message = "Responsibilities cannot be blank")
        @Size(max = 500, message = "Responsibilities cannot exceed 500 characters.")
        String responsibilities
) {}