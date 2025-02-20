package com.luscaer.Scrum_application.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record BacklogDTO(
        Long id,

        @NotBlank(message = "Description cannot be blank")
        @Size(max = 500, message = "Description must be less than 500 characters")
        String description,

        @NotBlank(message = "Priority cannot be blank")
        String priority,

        @NotBlank(message = "Status cannot be blank")
        String status,

        @NotNull(message = "Deadline cannot be null")
        @Future(message = "Deadline must be in the future")
        LocalDate deadline,

        @NotNull(message = "Complexity cannot be null")
        @Min(value = 1, message = "Complexity must be at least 1")
        @Max(value = 10, message = "Complexity must be at most 10")
        Integer complexity,

        @NotNull(message = "Project ID cannot be null")
        @Positive(message = "Project ID must be a positive number")
        Long projectId
) {}