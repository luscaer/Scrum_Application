package com.luscaer.Scrum_application.model;

import com.luscaer.Scrum_application.entity.ProjectEntity;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ProjectDTO(
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Expectations cannot be blank")
        @Size(max = 500, message = "Expectations must be at most 500 characters")
        String expectations,

        @NotNull(message = "Start date cannot be null")
        LocalDate startDate,

        @NotNull(message = "End date cannot be null")
        @Future(message = "End date must be in the future")
        LocalDate endDate,

        @NotNull(message = "Project owner ID cannot be null")
        @Positive(message = "Project owner ID must be a positive number")
        Long projectOwnerId
) {
        public static ProjectDTO fromEntity(ProjectEntity project) {
                return new ProjectDTO(
                        project.getId(),
                        project.getName(),
                        project.getExpectations(),
                        project.getStartDate(),
                        project.getEndDate(),
                        project.getProjectOwner() != null ? project.getProjectOwner().getId() : null
                );
        }
}