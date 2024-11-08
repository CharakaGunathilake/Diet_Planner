package edu.ICET.dto;


import edu.ICET.entity.QuizQuestionsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizOptions {
    private Long option_id;
    private Long questionId;
    private String option_text;
}
