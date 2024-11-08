package edu.ICET.controller;

import edu.ICET.dto.DietProgress;
import edu.ICET.service.custom.DietProgressService;
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
@RequestMapping("/diet-progress")
public class DietProgressController {

    private final DietProgressService dietProgressService;

    @PostMapping("/add-dietary-progress")
    public boolean addDietProgress(@Valid @RequestBody DietProgress dietProgress){
        log.info("Received DietProgress-> {}", dietProgress);
        return dietProgressService.save(dietProgress);
    }

    @GetMapping("/get-dietary-progress-byId/{id}")
    public DietProgress getDietProgressById(@PathVariable Long id){
        log.info("Requested DietProgress by the Id-> {}", id);
        return dietProgressService.search(id);
    }

    @PutMapping("/update-dietary-progress")
    public boolean updateDietProgress(@Valid @RequestBody DietProgress dietProgress){
        log.info("Updated DietProgress by the Id = {} as: {}",dietProgress.getId(),dietProgress);
        return dietProgressService.update(dietProgress);
    }

    @DeleteMapping("/delete-dietary-progress-byId/{id}")
    public boolean deleteDietProgressById(@PathVariable Long id){
        log.info("Deleted DietProgress by the Id-> {}", id);
        return dietProgressService.delete(id);
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
