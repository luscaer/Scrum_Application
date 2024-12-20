package com.luscaer.Scrum_application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luscaer.Scrum_application.enums.Gender;
import com.luscaer.Scrum_application.model.ProjectOwnerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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
    private String responsibilities;

    @OneToMany
    @JsonBackReference
    private List<ProjectEntity> projects;

    public ProjectOwnerEntity(ProjectOwnerDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.gender = Gender.valueOf(dto.gender());
        this.responsibilities = dto.responsibilities();
    }
}
