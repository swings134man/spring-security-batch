package com.lucas.bomkey.rest.dto;

import lombok.Data;

@Data
public class AuthCodeRequest {
    private String userEmail;
    private String password;
}
