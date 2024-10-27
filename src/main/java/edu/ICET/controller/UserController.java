package edu.ICET.controller;


import edu.ICET.dto.User;
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
@CrossOrigin
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    @PostMapping("add-user")
    public boolean addUser(@Valid @RequestBody User user){
        log.info("Received User-> {}", user);
        return userService.save(user);
    }

    @GetMapping("/get-user-byId/{id}")
    public User getUserById(@PathVariable Long id){
        log.info("Requested User by the Id-> {}", id);
        return userService.search(id);
    }

    @PutMapping("/update-diet-plan")
    public boolean updateUser(@Valid @RequestBody User user){
        log.info("Updated User as-> {}", user);
        return userService.update(user);
    }

    @DeleteMapping("/delete-diet-plan-byId/{id}")
    public boolean deleteUserById(@PathVariable Long id){
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
