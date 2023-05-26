package com.test.Mcq.controller;
import com.test.Mcq.dto.*;
import com.test.Mcq.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class of {@link McqController} is responsible for processing incoming
 * REST API requests, preparing a model, and returning the view to be rendered
 * as a response for corresponding API on the basis of implemented business
 *
 * @author deepak
 */

@RestController
@RequestMapping("api/v1")
public class McqController {

    private final QuestionServiceImpl questionService;
    @Autowired
    public McqController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    /**
     * This method is used for handling Post request of saving the questions data
     * successful response is built and returned.
     *
     * @param questionRequest it has data to save in db.
     * @return ResponseEntity gives response as per the defined in service class.
     */

    @PostMapping
    public ResponseEntity<QuestionResponse> addNewQuestion(@RequestBody QuestionRequest questionRequest){
        return this.questionService.addQuestion(questionRequest);
    }

    /**
     * This method is used for handling Post request of getting the next questions data
     * and check the correct answer after that,
     * successful response is built and returned.
     *
     * @param answerRequest it has data to check for correct answer.
     * @return ResponseEntity gives response as per the defined in service class.
     */

    @PostMapping("/next")
    public ResponseEntity<AnswerResponse> getCorrectAnswer(@RequestBody AnswerRequest answerRequest){
        return this.questionService.answerForTheQuestion(answerRequest);
    }

    /**
     * This method is used for handling GET request of getting the next questions data
     * successful response is built and returned.
     *
     * @return ResponseEntity gives response as per the defined in service class.
     */

    @GetMapping("/play")
    public ResponseEntity<NextQuestion> getQuestionData(){
        return this.questionService.getRandomQuestion();
    }

}
