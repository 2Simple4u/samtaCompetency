package com.test.Mcq.exception;

public class QuestionAlreadyPresentException extends RuntimeException{
    public QuestionAlreadyPresentException(String message){
        super(message);
    }
}
