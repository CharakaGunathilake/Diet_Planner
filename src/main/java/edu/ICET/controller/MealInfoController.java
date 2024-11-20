package edu.ICET.controller;


import edu.ICET.dto.MealInfo;
import edu.ICET.service.custom.MealInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/meal-info")
public class MealInfoController {

    private final MealInfoService mealInfoService;

    @PostMapping("/add-meal-info")
    public boolean addMealInfo(@Valid @RequestBody MealInfo mealInfo) {
        log.info("Received MealInfo-> {}", mealInfo);
        return mealInfoService.save(mealInfo);
    }

    @GetMapping("/get-meal-info-byId/{id}")
    public MealInfo getMealInfoById(@PathVariable Long id) {
        log.info("Requested MealInfo by the Id-> {}", id);
        return mealInfoService.search(id);
    }

    @PutMapping("/update-meal-info")
    public boolean updateMealInfo(@Valid @RequestBody MealInfo mealInfo) {
        log.info("Updated MealInfo by the Id= {} as: {}", mealInfo.getId(), mealInfo);
        return mealInfoService.update(mealInfo);
    }

    @DeleteMapping("/delete-meal-info-byId/{id}")
    public boolean deleteMealInfoById(@PathVariable Long id) {
        log.info("Deleted MealInfo by the Id-> {}", id);
        return mealInfoService.delete(id);
    }

    @GetMapping("/getAllMealInfo-byUserId/{id}")
    public List<MealInfo> getAllByUserId(@PathVariable Long id) {
        log.info("Requested All MealInfo by the userId-> {}", id);
        return mealInfoService.getAllByUserId(id);
    }

    @PutMapping("/setMealCompleted/{status}/{userId}/{mealId}/{dateCompleted}")
    public boolean setMealCompleted(@PathVariable("status") Boolean status, @PathVariable("userId") Long userId, @PathVariable("mealId") Long mealId, @PathVariable("dateCompleted") Date dateCompleted) {
        log.info("Updated meal by the id {} as {} by userId {}", mealId, status, userId);
        return mealInfoService.setMealCompleted(status, userId, mealId, dateCompleted);
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
