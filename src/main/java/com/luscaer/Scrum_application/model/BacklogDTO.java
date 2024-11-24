package com.luscaer.Scrum_application.model;

import java.time.LocalDate;

public record BacklogDTO(
        Long id,
        String description,
        String priority,
        String status,
        LocalDate deadline,
        Integer complexity,
        Long projectId
) {}
