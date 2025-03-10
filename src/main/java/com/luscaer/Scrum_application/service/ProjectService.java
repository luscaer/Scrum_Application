package com.luscaer.Scrum_application.service;

import com.luscaer.Scrum_application.entity.ProjectEntity;
import com.luscaer.Scrum_application.entity.ProjectOwnerEntity;
import com.luscaer.Scrum_application.exception.EntityNotFoundException;
import com.luscaer.Scrum_application.exception.InvalidRequestException;
import com.luscaer.Scrum_application.model.ProjectDTO;
import com.luscaer.Scrum_application.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectOwnerService projectOwnerService;

    public ProjectEntity convertDtoToEntity(ProjectDTO dto){
        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setId(null);
        projectEntity.setName(dto.name());
        projectEntity.setExpectations(dto.expectations());
        projectEntity.setStartDate(dto.startDate());
        projectEntity.setEndDate(dto.endDate());

        if (dto.projectOwnerId() != null){
            ProjectOwnerEntity projectOwner = projectOwnerService.getById(dto.projectOwnerId());
            projectEntity.setProjectOwner(projectOwner);
        }

        return projectEntity;
    }

    public ProjectEntity getById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project", id));
    }

    public List<ProjectEntity> getAllProjects() { return projectRepository.findAll(); }

    public ProjectEntity postProject(ProjectDTO dto) {
        ProjectEntity projectEntity = convertDtoToEntity(dto);

        ProjectEntity savedProject = projectRepository.save(projectEntity);

        if (savedProject.getProjectOwner() != null) {
            ProjectOwnerEntity projectOwner = savedProject.getProjectOwner();

            if (projectOwner.getProjects() == null) {
                projectOwner.setProjects(new ArrayList<>());
            }

            projectOwner.getProjects().add(projectEntity);

            projectOwnerService.updateProjectOwner(projectOwner);
        }

        return savedProject;
    }

    public ProjectEntity updateProject(ProjectDTO dto) {
        if (dto.id() == null) {
            throw new InvalidRequestException("ID is required for updating a Project");
        }

        ProjectEntity existingProject = getById(dto.id());

        if (dto.name() != null) {existingProject.setName(dto.name());}
        if (dto.expectations() != null) {existingProject.setExpectations(dto.expectations());}
        if (dto.startDate() != null) {existingProject.setStartDate(dto.startDate());}
        if (dto.endDate() != null) {existingProject.setEndDate(dto.endDate());}
        if (dto.projectOwnerId() != null) {
            ProjectOwnerEntity projectOwner = projectOwnerService.getById(dto.projectOwnerId());
            existingProject.setProjectOwner(projectOwner);
        }

        return projectRepository.save(existingProject);
    }

    public ProjectEntity updateProject(ProjectEntity entity) {
        if (entity.getId()== null) {
            throw new InvalidRequestException("ID is required for updating a Project");
        }

        ProjectEntity existingProject = getById(entity.getId());

        if (entity.getName() != null) {existingProject.setName(entity.getName());}
        if (entity.getExpectations() != null) {existingProject.setExpectations(entity.getExpectations());}
        if (entity.getStartDate() != null) {existingProject.setStartDate(entity.getStartDate());}
        if (entity.getEndDate() != null) {existingProject.setEndDate(entity.getEndDate());}
        if (entity.getProjectOwner() != null) {
            existingProject.setProjectOwner(entity.getProjectOwner());
        }

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Project", id);
        }
        projectRepository.deleteById(id);
    }
}
