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
        model.addAttribute("activePage", "projects");
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

    @GetMapping("/new-project")
    public ModelAndView showAddProjectForm(ProjectDTO projectDTO) {
        ModelAndView mv = new ModelAndView("projects/new-project");
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditProjectForm(@PathVariable Long id) {
        ProjectEntity project = projectService.getById(id);
        if (project != null) {
            ProjectDTO projectDTO = ProjectDTO.fromEntity(project);
            ModelAndView mv = new ModelAndView("projects/edit-project");
            mv.addObject(projectDTO);
            return mv;
        } else {
            return new ModelAndView("redirect:/projects");
        }
    }

    @PostMapping
    public ModelAndView createProject(@Valid @ModelAttribute("projectDTO") ProjectDTO projectDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("projects/new-project");
        }
        try {
            ProjectEntity project = projectService.postProject(projectDTO);
            return new ModelAndView("redirect:/projects/" + project.getId());
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("projects/new-project");
            mv.addObject("errorMessage", e.getMessage());
            return mv;
        }
    }

    @PostMapping("/{id}")
    public ModelAndView updateProject(@PathVariable Long id, @Valid @ModelAttribute("projectDTO") ProjectDTO projectDTO, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("projects/edit-project");
            mv.addObject("projectDTO", projectDTO);
            return mv;
        }
        try {
            ProjectEntity updatedProject = projectService.updateProject(projectDTO);
            return new ModelAndView("redirect:/projects/" + updatedProject.getId());
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("projects/edit-project");
            mv.addObject("projectDTO", projectDTO);
            mv.addObject("errorMessage", e.getMessage());
            return mv;
        }
    }
}