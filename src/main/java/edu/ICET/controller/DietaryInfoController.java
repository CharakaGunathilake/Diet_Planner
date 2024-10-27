package edu.ICET.controller;

import edu.ICET.dto.DietaryInfo;
import edu.ICET.service.custom.DietaryInfoService;
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
@RequestMapping("/DietaryInfo")
public class DietaryInfoController {

    private final DietaryInfoService dietaryInfoService;

    @PostMapping("add-dietary-info")
    public boolean addDietaryInfo(@Valid @RequestBody DietaryInfo dietaryInfo){
        log.info("Received DietaryInfo-> {}", dietaryInfo);
        return dietaryInfoService.save(dietaryInfo);
    }

    @GetMapping("/get-dietary-info-byId/{id}")
    public DietaryInfo getDietaryInfoById(@PathVariable Long id){
        log.info("Requested DietaryInfo by the Id-> {}", id);
        return dietaryInfoService.search(id);
    }

    @PutMapping("/update-dietary-info")
    public boolean updateDietaryInfo(@Valid @RequestBody DietaryInfo dietaryInfo){
        log.info("Updated DietaryInfo as-> {}", dietaryInfo);
        return dietaryInfoService.update(dietaryInfo);
    }

    @DeleteMapping("/delete-dietary_info-byId/{id}")
    public boolean deleteDietaryInfoById(@PathVariable Long id){
        log.info("Deleted DietaryInfo by the Id-> {}", id);
        return dietaryInfoService.delete(id);
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
