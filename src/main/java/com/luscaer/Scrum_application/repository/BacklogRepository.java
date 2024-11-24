package com.luscaer.Scrum_application.repository;

import com.luscaer.Scrum_application.entity.BacklogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<BacklogEntity, Long>{}
