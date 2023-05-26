package com.test.Mcq.dto;

import com.test.Mcq.entity.Category;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private long id;
    private String question;
    private String answer;
    private int value;
    private LocalDate airdate;
    private LocalDate created_at;
    private LocalDate updated_at;
    private long game_id;
    private String invalid_count;
    private Category category;

}
