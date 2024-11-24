package com.luscaer.Scrum_application.controller;

import com.luscaer.Scrum_application.entity.ProjectEntity;
import com.luscaer.Scrum_application.model.ProjectDTO;
import com.luscaer.Scrum_application.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> getProjectOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectEntity> createProjectOwner(@RequestBody ProjectDTO dto) {
        ProjectEntity project = projectService.postProject(dto);
        return ResponseEntity.ok(project);
    }

    @PutMapping
    public ResponseEntity<ProjectEntity> updateProjectOwner(@RequestBody ProjectDTO dto) {
        ProjectEntity updatedProject = projectService.updateProject(dto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
