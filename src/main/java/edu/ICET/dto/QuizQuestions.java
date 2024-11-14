package edu.ICET.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestions {
    private Long id;
    private String question;
    private String questionTypeEnum;
    private String description;
    private List<QuizOptions> optionSet;
}

