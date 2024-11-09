package edu.ICET.controller;


import edu.ICET.dto.QuizObject;
import edu.ICET.dto.QuizQuestions;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/quiz")
public class QuizController {
    private final QuizQuestionsService quizQuestionsService;

    @PostMapping("/add-question")
    public boolean addQuestion(@Valid @RequestBody QuizQuestions question) {
        log.info("Received QuizQuestion-> {}", question);
        return quizQuestionsService.save(question);
    }

    @PostMapping("/add-questionList")
    public void addQuestionList(@Valid @RequestBody List<QuizQuestions> questionsList) {
        log.info("Received QuizOptionList-> {}", questionsList);
        quizQuestionsService.saveAll(questionsList);
    }

    @GetMapping("/get-question-byId/{id}")
    public QuizQuestions getQuestionById(@PathVariable Long id) {
        log.info("Requested QuizQuestion by the Id-> {}", id);
        return quizQuestionsService.search(id);
    }

    @GetMapping("/getAll")
    public List<QuizObject> getAll() {
        log.info("Requested All QuizQuestions");
        return quizQuestionsService.getAllObjects();
    }
    @GetMapping("/getAllByType/{type}")
    public List<QuizObject> getAllByType(@PathVariable String type) {
        log.info("Requested All QuizQuestions by the type-> {}", type);
        return quizQuestionsService.getByObjectType(type.toUpperCase());
    }

    @PutMapping("/update-question-info")
    public boolean updateQuestion(@Valid @RequestBody QuizQuestions question) {
        log.info("Updated QuizQuestion by the Id= {} as: {}", question.getId(), question);
        return quizQuestionsService.update(question);
    }

    @DeleteMapping("/delete-question-byId/{id}")
    public boolean deleteQuestionById(@PathVariable Long id) {
        log.info("Deleted QuizQuestion by the Id-> {}", id);
        return quizQuestionsService.delete(id);
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
