package edu.ICET.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private Long id;
    @Size(min = 2, max = 50, message = "Username should be between 2 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])[A-Za-z\\d]{3,}$", message = "Invalid Username Format")
    private String username;
    @NotNull(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,}$", message = "Password too weak!")
    private String password;
    @NotNull
    private Date loginDate;
}
