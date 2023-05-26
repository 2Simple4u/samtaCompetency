package com.test.Mcq.service;
import com.test.Mcq.dto.*;
import com.test.Mcq.entity.Category;
import com.test.Mcq.entity.Questions;
import com.test.Mcq.exception.QuestionAlreadyPresentException;
import com.test.Mcq.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * This class contains the implementation of the methods present in
 * {@link questionService} for getting all the questionList, adding
 * question, validating question list
 *
 *
 * @author deepak
 */

@Service
public class QuestionServiceImpl implements questionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Method is used for CREATE operation for the respective question.
     * if data is already present then it throw error if not,
     * going to be saved to the DB
     *
     * @param request Request data to insert into DB
     *
     * @return question added to db
     *
     */

    @Override
    public ResponseEntity<QuestionResponse> addQuestion(QuestionRequest request) {
        var questionData =  questionRepository.findById(request.getId());
        final Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        if(questionData.isPresent()){
            throw new QuestionAlreadyPresentException("Question is already present");
        }else {
            Category category = new Category();
           category = category.builder()
                    .clues_count(request.getCategory().getClues_count())
                    .title(request.getCategory().getTitle())
                    .created_at(now)
                    .updated_at(now)
                    .build();
           var Question = Questions.builder()
                   .question(request.getQuestion())
                   .answer(request.getAnswer())
                   .airdate(now)
                   .created_at(now)
                   .updated_at(now)
                   .value(request.getValue())
                   .game_id(request.getGame_id())
                   .category(category)
                   .build();
           this.questionRepository.save(Question);
           return new ResponseEntity(Question, HttpStatus.CREATED);
        }
    }

    /**
     * Method is used for GET operation for the respective question answer.
     * if request data answer is matched with db particular question answer,
     * going to be saved to the send that answer is correct and send new question
     *
     * @param request Request data to get from DB
     *
     * @return correct response and next question
     *
     */

    @Override
    public ResponseEntity<AnswerResponse> answerForTheQuestion(AnswerRequest request) {
        var answerData= questionRepository.findById(request.getId()).orElseThrow();
        var nextQuestionData= questionRepository.findById(request.getId()+1);

        //  Validation for if answer is not correct
        if(!answerData.getAnswer().equals(request.getAnswer()) && !answerData.equals(null)){
            new ResponseEntity<>("Incorrect answer",HttpStatus.NOT_FOUND);
        }else
            //  Validation for if there is next question and answer is correct
        if(answerData.getAnswer().equals(request.getAnswer()) && nextQuestionData.isPresent()){
            NextQuestion nextQuestion = new NextQuestion();
            nextQuestion = nextQuestion.builder()
                    .question_id(nextQuestionData.get().getId())
                    .question(nextQuestionData.get().getQuestion())
                    .build();
           AnswerResponse answerResponse =  AnswerResponse.builder()
                    .correct_answer(answerData.getAnswer())
                    .nextQuestion(nextQuestion)
                    .build();
            return new ResponseEntity<>(answerResponse,HttpStatus.OK);

            //  Validation for if next question empty
        }else if(answerData.getAnswer().equals(request.getAnswer()) && nextQuestionData.isEmpty()){
            NextQuestion nextQuestion = new NextQuestion();
            nextQuestion.setQuestion(null);
            nextQuestion.setQuestion_id(0);
            var answerResponse =  AnswerResponse.builder()
                    .correct_answer(answerData.getAnswer())
                    .nextQuestion(nextQuestion)
                    .build();
            return new ResponseEntity<>(answerResponse,HttpStatus.OK);
        }
        return new ResponseEntity("data not found",HttpStatus.NOT_FOUND);
    }


    /**
     * Method is used for GET operation for the random question from db.

     *
     * @return  next question  response;
     *
     */

    @Override
    public ResponseEntity<NextQuestion> getRandomQuestion() {
        var QuestionList = questionRepository.findAll();
        List<Questions> shuffledList = new ArrayList(QuestionList);
        Collections.shuffle(shuffledList);
        //  Validation for if list is empty
        if(!QuestionList.isEmpty()){
           NextQuestion nextQuestion = NextQuestion.builder()
                    .question(shuffledList.get(0).getQuestion())
                    .question_id(shuffledList.get(0).getId())
                    .build();
            return new ResponseEntity<>(nextQuestion,HttpStatus.OK);
        }
        return new ResponseEntity("Data not found",HttpStatus.NOT_FOUND);
    }



}
