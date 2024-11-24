package com.luscaer.Scrum_application.controller;

import com.luscaer.Scrum_application.entity.BacklogEntity;
import com.luscaer.Scrum_application.model.BacklogDTO;
import com.luscaer.Scrum_application.service.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/backlogs")
public class BacklogController {
    @Autowired
    private BacklogService backlogService;

    @GetMapping("/{id}")
    public ResponseEntity<BacklogEntity> getBacklogById(@PathVariable Long id) {
        return ResponseEntity.ok(backlogService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BacklogEntity> postBacklog(@RequestBody BacklogDTO dto) {
        BacklogEntity backlog = backlogService.postBacklog(dto);
        return ResponseEntity.ok(backlog);
    }

    @PutMapping
    public ResponseEntity<BacklogEntity> updateBacklog(@RequestBody BacklogDTO dto) {
        BacklogEntity updatedBacklog = backlogService.updateProject(dto);
        return ResponseEntity.ok(updatedBacklog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBacklog(@PathVariable Long id) {
        backlogService.deleteBacklog(id);
        return ResponseEntity.noContent().build();
    }
}
