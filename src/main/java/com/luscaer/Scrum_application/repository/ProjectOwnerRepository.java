package com.luscaer.Scrum_application.repository;

import com.luscaer.Scrum_application.entity.ProjectOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectOwnerRepository extends JpaRepository<ProjectOwnerEntity, Long> {}
