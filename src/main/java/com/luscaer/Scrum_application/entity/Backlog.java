package com.luscaer.Scrum_application.entity;

import com.luscaer.Scrum_application.enums.BacklogStatus;
import com.luscaer.Scrum_application.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
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

    private int complexity;

    @ManyToOne
    private Project project;
}
