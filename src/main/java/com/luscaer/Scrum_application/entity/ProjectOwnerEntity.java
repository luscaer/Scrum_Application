package com.luscaer.Scrum_application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luscaer.Scrum_application.enums.Gender;
import com.luscaer.Scrum_application.model.ProjectOwnerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectOwnerEntity extends Person{
    @NotNull
    @Column(nullable = false)
    @Size(max = 500, message = "Responsibilities cannot exceed 500 characters.")
    private String responsibilities;

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"projectOwner","backlogs"})
    private List<ProjectEntity> projects;

    public ProjectOwnerEntity(ProjectOwnerDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.gender = Gender.valueOf(dto.gender());
        this.responsibilities = dto.responsibilities();
    }
}
