package com.luscaer.Scrum_application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    private String name;

    @NotNull
    @Column(nullable = false)
    private String expectations;

    @PastOrPresent
    private LocalDate startDate;

    @FutureOrPresent
    private LocalDate endDate;

    @ManyToOne
    @JsonManagedReference
    private ProjectOwnerEntity projectOwner;

    @OneToMany(mappedBy = "project")
    @JsonBackReference
    private List<BacklogEntity> backlogs;
}
