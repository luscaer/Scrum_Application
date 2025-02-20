package com.luscaer.Scrum_application.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ProjectDTO(
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Expectations cannot be blank")
        String expectations,

        @NotNull(message = "Start date cannot be null")
        @FutureOrPresent(message = "Start date must be in the present or future")
        LocalDate startDate,

        @NotNull(message = "End date cannot be null")
        @Future(message = "End date must be in the future")
        LocalDate endDate,

        @NotNull(message = "Project owner ID cannot be null")
        @Positive(message = "Project owner ID must be a positive number")
        Long projectOwnerId
) {}