package com.test.Mcq.dto;


import com.test.Mcq.entity.Questions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {

    private String correct_answer;
    private NextQuestion nextQuestion;
}
