package com.example.toy_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupRequest {
    private String email;
    private String password;
    private String name;
}
