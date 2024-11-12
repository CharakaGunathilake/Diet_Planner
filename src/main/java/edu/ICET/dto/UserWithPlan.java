package edu.ICET.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPlan {
    private Long id;
    @NotNull(message = "User info can't be empty")
    private User user;
    @NotNull(message = "Login info can't be empty")
    private Login login;
    @NotNull(message = "Diet info can't be empty")
    private DietaryInfo dietaryInfo;
}
