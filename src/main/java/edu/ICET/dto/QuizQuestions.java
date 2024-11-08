package edu.ICET.dto;

import edu.ICET.entity.QuestionType;
import edu.ICET.entity.QuizOptionsEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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

