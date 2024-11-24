package com.luscaer.Scrum_application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String expectations;

    @ManyToOne
    private ProjectOwner projectOwner;

    @OneToMany(mappedBy = "project")
    private List<Backlog> backlog;
}
