package edu.ICET.controller;


import edu.ICET.dto.QuizOptions;
import edu.ICET.dto.QuizQuestions;
import edu.ICET.service.custom.QuizOptionsService;
import edu.ICET.service.custom.QuizQuestionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/quiz-options")
public class QuizOptionController {
    private final QuizOptionsService quizOptionsService;

    @PostMapping("/add-option")
    public boolean addOption(@Valid @RequestBody QuizOptions option) {
        log.info("Received QuizOption-> {}", option);
        return quizOptionsService.save(option);

    }

    @PostMapping("add-optionList")
    public void addOptionList(@Valid @RequestBody List<QuizOptions> optionsList) {
        log.info("Received QuizOptionList-> {}", optionsList);
        quizOptionsService.saveAll(optionsList);
    }

    @GetMapping("/get-option-byId/{id}")
    public QuizOptions getOptionById(@PathVariable Long id) {
        log.info("Requested QuizOption by the Id-> {}", id);
        return quizOptionsService.search(id);
    }

    @PutMapping("/update-question-info")
    public boolean updateOption(@Valid @RequestBody QuizOptions option) {
        log.info("Updated QuizOption by the Id= {} as: {}", option.getOption_id(), option);
        return quizOptionsService.update(option);
    }

    @DeleteMapping("/delete-question-byId/{id}")
    public boolean deleteOptionById(@PathVariable Long id) {
        log.info("Deleted QuizOption by the Id-> {}", id);
        return quizOptionsService.delete(id);
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
