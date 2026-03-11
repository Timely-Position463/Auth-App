package com.portfolio.project.Auth_App.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public record ErrorResponse(String error, HttpStatus status, HttpStatusCode statusCode) {

}
