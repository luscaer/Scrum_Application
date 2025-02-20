package com.luscaer.Scrum_application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestErrorMessage {
    private String message;
    private int statusCode;
    private Map<String, String> errors;

    public RestErrorMessage(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.errors = null;
    }
}
