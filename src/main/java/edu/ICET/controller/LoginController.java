package edu.ICET.controller;


import edu.ICET.dto.Login;
import edu.ICET.service.custom.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login) {
        log.info("Login requested by the user-> {}", login);
        Map<String, String> response = loginService.verify(login);
        if (response.containsKey("jwt")) {
            System.out.println(response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message", "unauthorized login");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @PostMapping("/add-login")
    public boolean addLogin(@Valid @RequestBody Login login) {
        log.info("Received Login-> {}", login);
        return loginService.save(login);
    }

    @PutMapping("/update-login-info")
    public boolean updateLogin(@Valid @RequestBody Login login) {
        log.info("Updated Login by the Id = {} as: {}", login.getId(), login);
        return loginService.update(login);
    }

    @DeleteMapping("/delete-login-byId/{id}")
    public boolean deleteLoginById(@PathVariable Long id) {
        log.info("Deleted Login by the Id-> {}", id);
        return loginService.delete(id);
    }

    @GetMapping("/validate")
    public boolean checkUsername(@RequestParam String username) {
        log.info("Requested availability of username-> {}", username);
        return loginService.checkUsername(username);
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
