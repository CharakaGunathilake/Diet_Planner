package edu.ICET.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @NotNull(message = "First name Cannot be empty!")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    @NotNull(message = "Last name Cannot be empty!")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String lastName;
    @NotNull(message = "Gender Cannot be empty!")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Invalid gender")
    private String gender;
    @NotNull(message = "Birthday Cannot be empty!")
    @Past(message = "Invalid Birthday")
    private Date birthDay;
    @NotNull(message = "Age cannot be empty!")
    @Min(value = 5, message = "Age too low")
    @Max(value = 100, message = "Invalid age")
    private Integer age;
    @NotNull(message = "Height cannot be empty!")
    @Min(value = 2, message = "Not a valid height")
    @Max(value = 240, message = "Not a valid height")
    private Double height;
    @NotNull(message = "Weight cannot be empty!")
    @Min(value = 10, message = "Not a valid weight")
    @Max(value = 150, message = "Not a valid weight")
    private Double weight;
    @NotNull(message = "Email cannot be empty!")
    @Size(min = 10, max = 50, message = "Email must be between 2 and 50 characters")
    @Email(message = "Invalid Email Format")
    private String email;
    @NotNull
    @Pattern(regexp = "^(true|false)$", message = "Status must be either 'true' or 'false'")
    private String status;
    @NotNull
    private Date regDate;
}
