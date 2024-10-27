package edu.ICET.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class Login {
    private Long id;
    @Size(min = 2, max = 50, message = "Username should be between 2 and 50 characters")
    private String username;
    @NotNull(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,}$\n", message = "Password too weak!")
    private String password;
    @NotNull
    private Date loginDate;
}
