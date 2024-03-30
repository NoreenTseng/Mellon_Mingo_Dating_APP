package com.sda.datingapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "username is blank")
    private String username;

    @NotBlank(message = "password is blank")
    private String password;
}
