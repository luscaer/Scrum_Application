package com.luscaer.Scrum_application.controller;

import com.luscaer.Scrum_application.entity.BacklogEntity;
import com.luscaer.Scrum_application.enums.BacklogStatus;
import com.luscaer.Scrum_application.enums.Priority;
import com.luscaer.Scrum_application.model.BacklogDTO;
import com.luscaer.Scrum_application.service.BacklogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/backlogs")
public class BacklogViewController {
    @Autowired
    private BacklogService backlogService;

    @GetMapping
    public String getAllBacklogs(Model model) {
        List<BacklogEntity> backlogs = backlogService.getAllBacklogs();
        model.addAttribute("backlogs", backlogs);
        model.addAttribute("activePage", "backlogs");
        return "backlogs/backlogs";
    }

    @GetMapping("/{id}")
    public String getBacklogById(@PathVariable Long id, Model model) {
        BacklogEntity backlog = backlogService.getById(id);
        if (backlog != null) {
            model.addAttribute("backlog", backlog);
            return "backlogs/backlog-detail";
        } else {
            return "redirect:/backlogs";
        }
    }

    @GetMapping("/new-backlog")
    public ModelAndView showAddBacklogForm(BacklogDTO backlogDTO) {
        ModelAndView mv = new ModelAndView("backlogs/new-backlog");
        mv.addObject("priorityE", Priority.values());
        mv.addObject("backlogStatus", BacklogStatus.values());
        return mv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditbacklogForm(@PathVariable Long id) {
        BacklogEntity backlog = backlogService.getById(id);
        if (backlog != null) {
            BacklogDTO backlogDTO = BacklogDTO.fromEntity(backlog);
            ModelAndView mv = new ModelAndView("backlogs/edit-backlog");
            mv.addObject("priorityE", Priority.values());
            mv.addObject("backlogStatus", BacklogStatus.values());
            mv.addObject("backlogDTO", backlogDTO);
            return mv;
        } else {
            return new ModelAndView("redirect:/backlogs");
        }
    }

    @PostMapping
    public ModelAndView createBacklog(@Valid @ModelAttribute("backlogDTO") BacklogDTO backlogDTO, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("backlogs/new-backlog");
            mv.addObject("priorityE", Priority.values());
            mv.addObject("backlogStatus", BacklogStatus.values());
            return mv;
        }
        try {
            BacklogEntity backlog = backlogService.postBacklog(backlogDTO);
            return new ModelAndView("redirect:/backlogs/" + backlog.getId());
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("backlogs/new-backlog");
            mv.addObject("priorityE", Priority.values());
            mv.addObject("backlogStatus", BacklogStatus.values());
            mv.addObject("errorMessage", e.getMessage());
            return mv;
        }
    }

    @PostMapping("/{id}")
    public ModelAndView updateBacklog(@PathVariable Long id, @Valid @ModelAttribute("backlogDTO") BacklogDTO backlogDTO, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("backlogs/edit-backlog");
            mv.addObject("priorityE", Priority.values());
            mv.addObject("backlogStatus", BacklogStatus.values());
            mv.addObject("backlogDTO", backlogDTO);
            return mv;
        }
        try {
            BacklogEntity updatedbacklog = backlogService.updateBacklog(backlogDTO);
            return new ModelAndView("redirect:/backlogs/" + updatedbacklog.getId());
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("backlogs/edit-backlog");
            mv.addObject("priorityE", Priority.values());
            mv.addObject("backlogStatus", BacklogStatus.values());
            mv.addObject("backlogDTO", backlogDTO);
            mv.addObject("errorMessage", e.getMessage());
            return mv;
        }
    }

}
