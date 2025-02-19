package com.luscaer.Scrum_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    private String name;

    @NotNull
    @Column(nullable = false)
    @Size(max = 1000, message = "Responsibilities cannot exceed 1000 characters.")
    private String expectations;

    @PastOrPresent
    private LocalDate startDate;

    @FutureOrPresent
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnoreProperties({"projects"})
    private ProjectOwnerEntity projectOwner;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"project"})
    private List<BacklogEntity> backlogs;
}
