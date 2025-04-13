package com.soulittude.e_commerce.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}