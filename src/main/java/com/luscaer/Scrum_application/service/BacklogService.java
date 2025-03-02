package com.luscaer.Scrum_application.service;

import com.luscaer.Scrum_application.entity.BacklogEntity;
import com.luscaer.Scrum_application.entity.ProjectEntity;
import com.luscaer.Scrum_application.enums.BacklogStatus;
import com.luscaer.Scrum_application.enums.Priority;
import com.luscaer.Scrum_application.exception.EntityNotFoundException;
import com.luscaer.Scrum_application.exception.InvalidRequestException;
import com.luscaer.Scrum_application.model.BacklogDTO;
import com.luscaer.Scrum_application.repository.BacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BacklogService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectService projectService;

    public BacklogEntity convertDtoToEntity(BacklogDTO dto){
        BacklogEntity backlogEntity = new BacklogEntity();

        backlogEntity.setId(null);
        backlogEntity.setDescription(dto.description());
        backlogEntity.setPriority(Priority.valueOf(dto.priority()));
        backlogEntity.setStatus(BacklogStatus.valueOf(dto.status()));
        backlogEntity.setDeadline(dto.deadline());
        backlogEntity.setComplexity(dto.complexity());

        if (dto.projectId() != null){
            ProjectEntity project = projectService.getById(dto.projectId());
            backlogEntity.setProject(project);
        }

        if (backlogEntity.isDeadlineAfterEndDate()) {
            throw new InvalidRequestException("Deadline must be before or equal to the project's end date");
        }

        return backlogEntity;
    }

    public BacklogEntity getById(Long id) {
        return backlogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Backlog", id));
    }

    public List<BacklogEntity> getAllBacklogs() { return backlogRepository.findAll(); }

    public BacklogEntity postBacklog(BacklogDTO dto) {
        BacklogEntity backlogEntity = convertDtoToEntity(dto);

        BacklogEntity savedBacklog = backlogRepository.save(backlogEntity);

        if (savedBacklog.getProject() != null) {
            ProjectEntity project = savedBacklog.getProject();

            if (project.getBacklogs() == null) {
                project.setBacklogs(new ArrayList<>());
            }

            project.getBacklogs().add(backlogEntity);

            projectService.updateProject(project);
        }

        return savedBacklog;
    }

    public BacklogEntity updateBacklog(BacklogDTO dto) {
        if (dto.id() == null) {
            throw new InvalidRequestException("ID is required for updating a Backlog");
        }

        BacklogEntity existingBacklog = getById(dto.id());

        if (dto.description() != null) {existingBacklog.setDescription(dto.description());}
        if (dto.priority() != null) {existingBacklog.setPriority(Priority.valueOf(dto.priority()));}
        if (dto.status() != null) {existingBacklog.setStatus(BacklogStatus.valueOf(dto.status()));}
        if (dto.deadline() != null) {existingBacklog.setDeadline(dto.deadline());}
        if (dto.complexity() != null) {existingBacklog.setComplexity(dto.complexity());}
        if (dto.projectId() != null) {
            ProjectEntity project = projectService.getById(dto.projectId());
            existingBacklog.setProject(project);
        }

        return backlogRepository.save(existingBacklog);
    }

    public void deleteBacklog(Long id) {
        if (!backlogRepository.existsById(id)) {
            throw new EntityNotFoundException("Backlog", id);
        }
        backlogRepository.deleteById(id);
    }
}
