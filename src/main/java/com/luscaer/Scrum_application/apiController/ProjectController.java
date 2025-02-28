package com.luscaer.Scrum_application.apiController;

import com.luscaer.Scrum_application.entity.ProjectEntity;
import com.luscaer.Scrum_application.model.ProjectDTO;
import com.luscaer.Scrum_application.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectEntity>> getAllProjects() {
        List<ProjectEntity> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectEntity> createProject(@Valid @RequestBody ProjectDTO dto) {
        ProjectEntity project = projectService.postProject(dto);
        return ResponseEntity.ok(project);
    }

    @PutMapping
    public ResponseEntity<ProjectEntity> updateProject(@Valid @RequestBody ProjectDTO dto) {
        ProjectEntity updatedProject = projectService.updateProject(dto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
