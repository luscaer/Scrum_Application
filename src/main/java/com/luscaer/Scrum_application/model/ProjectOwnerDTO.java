package com.luscaer.Scrum_application.model;

public record ProjectOwnerDTO(
        Long id,
        String name,
        String email,
        String phone,
        String gender,
        String responsibilities
) {}
