package com.luscaer.Scrum_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luscaer.Scrum_application.enums.BacklogStatus;
import com.luscaer.Scrum_application.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BacklogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private BacklogStatus status;

    @FutureOrPresent
    private LocalDate deadline;

    @Min(value = 1, message = "The complexity must be at least 1.")
    private Integer complexity;

    @ManyToOne
    @JsonIgnoreProperties({"backlogs"})
    private ProjectEntity project;

    @AssertTrue(message = "Deadline must be before or equal to the project's end date")
    private boolean isDeadlineBeforeEndDate() {
        if (project == null || deadline == null) {
            return true;
        }
        return !deadline.isAfter(project.getEndDate());
    }
}
