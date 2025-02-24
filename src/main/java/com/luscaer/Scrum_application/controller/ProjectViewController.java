package com.luscaer.Scrum_application.controller;

import com.luscaer.Scrum_application.entity.ProjectEntity;
import com.luscaer.Scrum_application.model.ProjectDTO;
import com.luscaer.Scrum_application.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectViewController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String getAllProjects(Model model) {
        List<ProjectEntity> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/projects";
    }

    @GetMapping("/{id}")
    public String getProjectDetails(@PathVariable Long id, Model model) {
        ProjectEntity project = projectService.getById(id);
        if (project != null) {
            model.addAttribute("project", project);
            return "projects/project-detail";
        } else {
            return "redirect:/projects";
        }
    }

    @GetMapping("/new")
    public String showAddProjectForm(Model model) {
        model.addAttribute("projectDTO", new ProjectDTO(null, "", "", null, null, null));
        return "projects/new-project";
    }

    @PostMapping
    public String addProject(@Valid @ModelAttribute("projectDTO") ProjectDTO projectDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Se houver erros de validação, retorne para o formulário com as mensagens de erro
            return "projects/new-project";
        }
        // Lógica para salvar o projeto
        projectService.postProject(projectDTO);
        return "redirect:/projects"; // Redireciona para a listagem de projetos após a adição
    }
}