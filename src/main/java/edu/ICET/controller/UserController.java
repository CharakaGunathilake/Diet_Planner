package edu.ICET.controller;


import edu.ICET.dto.DietaryInfo;
import edu.ICET.dto.Login;
import edu.ICET.dto.User;
import edu.ICET.dto.UserWithPlan;
import edu.ICET.service.custom.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add-user-with-plan")
    public boolean addUserWithPlan(@Valid @RequestBody UserWithPlan userWithPlan) {
        log.info("Received UserWithPlan-> {}", userWithPlan.getUser());
        log.info("Received UserLogin-> {}", userWithPlan.getLogin());
        log.info("Received UserDietPlan-> {}", userWithPlan.getDietPlan());
        log.info("Received UserDietaryInfo-> {}", userWithPlan.getDietaryInfo());
        return userService.saveNewUser(userWithPlan);
    }
    @PostMapping("/add-user")
    public boolean addUser(@Valid @RequestBody User user) {
        log.info("Received User-> {}", user);
        return userService.save(user);
    }

    @GetMapping("/get-user-byId/{id}")
    public User getUserById(@PathVariable Long id) {
        log.info("Requested User by the Id-> {}", id);
        return userService.search(id);
    }

    @PutMapping("/update-user-info")
    public boolean updateUser(@Valid @RequestBody User user) {
        log.info("Updated User by the Id= {} as: {}", user.getId(), user);
        return userService.update(user);
    }

    @DeleteMapping("/delete-user-byId/{id}")
    public boolean deleteUserById(@PathVariable Long id) {
        log.info("Deleted User by the Id-> {}", id);
        return userService.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
