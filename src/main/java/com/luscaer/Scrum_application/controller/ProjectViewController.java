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
import org.springframework.web.servlet.ModelAndView;

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
    public String getProjectById(@PathVariable Long id, Model model) {
        ProjectEntity project = projectService.getById(id);
        if (project != null) {
            model.addAttribute("project", project);
            return "projects/project-detail";
        } else {
            return "redirect:/projects";
        }
    }

    @GetMapping("/new")
    public ModelAndView showAddProjectForm() {
        ModelAndView mv = new ModelAndView("projects/new-project");
        mv.addObject("projectDTO", new ProjectDTO(null, "", "", null, null, null));
        return mv;
    }

    @PostMapping
    public ModelAndView createProject(@Valid @ModelAttribute("projectDTO") ProjectDTO projectDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("projects/new-project");
        }
        projectService.postProject(projectDTO);
        return new ModelAndView("redirect:/projects");
    }
}