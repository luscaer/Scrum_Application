package com.luscaer.Scrum_application.controller;

import com.luscaer.Scrum_application.entity.ProjectOwnerEntity;
import com.luscaer.Scrum_application.model.ProjectOwnerDTO;
import com.luscaer.Scrum_application.service.ProjectOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/project-owners")
public class ProjectOwnerController {
    @Autowired
    private ProjectOwnerService projectOwnerService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectOwnerEntity> getProjectOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(projectOwnerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectOwnerEntity> createProjectOwner(@RequestBody ProjectOwnerDTO dto) {
        ProjectOwnerEntity projectOwner = projectOwnerService.postProjectOwner(dto);
        return ResponseEntity.ok(projectOwner);
    }

    @PutMapping
    public ResponseEntity<ProjectOwnerEntity> updateProjectOwner(@RequestBody ProjectOwnerDTO dto) {
        ProjectOwnerEntity updatedProjectOwner = projectOwnerService.putProjectOwner(dto);
        return ResponseEntity.ok(updatedProjectOwner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectOwner(@PathVariable Long id) {
        projectOwnerService.deleteProjectOwner(id);
        return ResponseEntity.noContent().build();
    }
}
