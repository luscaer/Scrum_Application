package com.luscaer.Scrum_application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectOwner extends Person{
    @NotNull
    @Column(nullable = false)
    private String responsibilities;

    @OneToMany
    private List<Project> projects;
}
