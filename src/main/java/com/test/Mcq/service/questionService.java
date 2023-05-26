package com.test.Mcq.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.Mcq.dto.*;
import org.springframework.http.ResponseEntity;

public interface questionService {

    /**
     * This method is used to Create ChargeList
     *
     * @param request Request Data to Insert into DB
     * @return Returning Question after Saving in the DB
     */
    public ResponseEntity<QuestionResponse> addQuestion(QuestionRequest request) ;

    /**
     * This method is used to get correct answer for question and also with next question
     *
     * @param request Request Data to get from DB
     * @return Returning AnswerResponse after validating from the DB
     */
    public ResponseEntity<AnswerResponse> answerForTheQuestion(AnswerRequest request) throws JsonProcessingException;

    /**
     * This method is used to get question form DB
     *
     * @return Returning NextQuestion from the DB
     */
    public ResponseEntity<NextQuestion> getRandomQuestion();

}
