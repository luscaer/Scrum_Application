package com.luscaer.Scrum_application.repository;

import com.luscaer.Scrum_application.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {}
