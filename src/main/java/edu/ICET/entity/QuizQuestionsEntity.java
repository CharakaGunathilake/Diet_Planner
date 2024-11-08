package edu.ICET.entity;

import edu.ICET.dto.QuizOptions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "QuizQuestions")
public class QuizQuestionsEntity {
    @Id
    private Long id;
    private String question;
    private String questionTypeEnum;
    private String description;
}

