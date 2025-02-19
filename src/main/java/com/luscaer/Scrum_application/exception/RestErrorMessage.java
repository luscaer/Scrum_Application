package com.luscaer.Scrum_application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestErrorMessage {
    private String message;
    private int statusCode;
}
