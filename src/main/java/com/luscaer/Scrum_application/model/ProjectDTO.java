package com.luscaer.Scrum_application.model;

import java.time.LocalDate;

public record ProjectDTO(
        Long id,
        String name,
        String expectations,
        LocalDate startDate,
        LocalDate endDate,
        Long projectOwnerId
) {}
