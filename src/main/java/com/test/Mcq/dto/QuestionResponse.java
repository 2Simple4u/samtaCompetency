package com.test.Mcq.dto;

import com.test.Mcq.entity.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private long id;
    private String question;
    private String answer;
    private int value;
    private LocalDate airdate;
    private LocalDate created_at;
    private LocalDate updated_at;
    private long game_id;
    private String invalid_count;
    private long category_id;
    private Category category;

}
