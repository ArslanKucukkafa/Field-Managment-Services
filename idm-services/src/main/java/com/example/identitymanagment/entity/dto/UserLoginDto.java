package com.example.identitymanagment.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginDto {
    @Schema(example = "@devetuncay19")
    private String username;
    @Schema(example = "1arslan_docker_ps")
    private String password;
}
