package com.example.library.controller.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Integer id;
    private String username;
    private String email;
    private String token;
}
