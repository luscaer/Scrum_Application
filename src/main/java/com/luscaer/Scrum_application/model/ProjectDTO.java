package com.luscaer.Scrum_application.model;

public record ProjectDTO(
        Long id,
        String name,
        String expectations,
        Long projectOwnerId
) {}
