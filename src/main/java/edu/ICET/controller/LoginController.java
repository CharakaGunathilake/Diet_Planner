package edu.ICET.controller;


import edu.ICET.dto.Login;
import edu.ICET.service.custom.LoginServiceMy;
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
@RequestMapping("/login")
public class LoginController {

    private final LoginServiceMy loginService;

    @PostMapping("add-login")
    public boolean addLogin(@Valid @RequestBody Login login){
        log.info("Received Login-> {}", login);
        return loginService.save(login);
    }

    @GetMapping("/get-login-byId/{id}")
    public Login getLoginById(@PathVariable Long id){
        log.info("Requested Login by the Id-> {}", id);
        return loginService.search(id);
    }

    @PutMapping("/update-login-info")
    public boolean updateLogin(@Valid @RequestBody Login login){
        log.info("Updated Login by the Id = {} as: {}", login.getId(), login);
        return loginService.update(login);
    }

    @DeleteMapping("/delete-login-byId/{id}")
    public boolean deleteLoginById(@PathVariable Long id){
        log.info("Deleted Login by the Id-> {}", id);
        return loginService.delete(id);
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
