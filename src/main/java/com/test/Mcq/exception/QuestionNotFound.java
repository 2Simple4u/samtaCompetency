package com.test.Mcq.exception;

public class QuestionNotFound extends RuntimeException{
    public QuestionNotFound(String message){
        super(message);
    }
}
