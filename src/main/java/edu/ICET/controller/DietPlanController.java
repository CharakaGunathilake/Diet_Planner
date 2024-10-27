package edu.ICET.controller;


import edu.ICET.dto.DietPlan;
import edu.ICET.service.custom.DietPlanService;
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
@RequestMapping("/DietPlan")
public class DietPlanController {

    private final DietPlanService dietPlanService;

    @PostMapping("add-diet-plan")
    public boolean addDietPlan(@Valid @RequestBody DietPlan dietPlan){
        log.info("Received DietPlan-> {}", dietPlan);
        return dietPlanService.save(dietPlan);
    }

    @GetMapping("/get-diet-plan-byId/{id}")
    public DietPlan getDietPlanById(@PathVariable Long id){
        log.info("Requested DietPlan by the Id-> {}", id);
        return dietPlanService.search(id);
    }

    @PutMapping("/update-diet-plan")
    public boolean updateDietPlan(@Valid @RequestBody DietPlan dietPlan){
        log.info("Updated DietPlan as-> {}", dietPlan);
        return dietPlanService.update(dietPlan);
    }

    @DeleteMapping("/delete-diet-plan-byId/{id}")
    public boolean deleteDietPlanById(@PathVariable Long id){
        log.info("Deleted DietPlan by the Id-> {}", id);
        return dietPlanService.delete(id);
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
