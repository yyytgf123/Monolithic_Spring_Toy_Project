package com.example.toy_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginRequest {
    private String email;
    private String password;
}
