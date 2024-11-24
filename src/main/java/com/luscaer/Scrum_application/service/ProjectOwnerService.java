package com.luscaer.Scrum_application.service;

import com.luscaer.Scrum_application.entity.ProjectOwnerEntity;
import com.luscaer.Scrum_application.enums.Gender;
import com.luscaer.Scrum_application.model.ProjectOwnerDTO;
import com.luscaer.Scrum_application.repository.ProjectOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectOwnerService {
    @Autowired
    private ProjectOwnerRepository projectOwnerRepository;

    public ProjectOwnerEntity getById(Long id){
        return projectOwnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ProjectOwner not found with ID: " + id));
    }

    public ProjectOwnerEntity postProjectOwner(ProjectOwnerDTO dto){
        ProjectOwnerEntity projectOwner = new ProjectOwnerEntity(dto);
        return projectOwnerRepository.save(projectOwner);
    }

    public ProjectOwnerEntity putProjectOwner(ProjectOwnerDTO dto){
        if (dto.id() == null) {
            throw new IllegalArgumentException("ID is required for updating a ProjectOwner");
        }

        ProjectOwnerEntity existingProjectOwner =getById(dto.id());

        if (dto.name() != null) {existingProjectOwner.setName(dto.name());}
        if (dto.email() != null) {existingProjectOwner.setEmail(dto.email());}
        if (dto.phone() != null) {existingProjectOwner.setPhone(dto.phone());}
        if (dto.gender() != null) {existingProjectOwner.setGender(Gender.valueOf(dto.gender()));}
        if (dto.responsibilities() != null) {existingProjectOwner.setResponsibilities(dto.responsibilities());}

        return projectOwnerRepository.save(existingProjectOwner);
    }

    public void deleteProjectOwner(Long id) {
        if (!projectOwnerRepository.existsById(id)) {
            throw new IllegalArgumentException("ProjectOwner not found with ID: " + id);
        }
        projectOwnerRepository.deleteById(id);
    }
}
