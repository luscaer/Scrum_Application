package com.luscaer.Scrum_application.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " not found with ID: " + id);
    }
}
