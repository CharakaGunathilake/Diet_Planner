package edu.ICET.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizObject {
    private Long id;
    private QuizQuestions quizQuestion;
    private List<QuizOptions> quizOption;
}
