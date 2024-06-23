package com.quiz.question_service.Controller;


import com.quiz.question_service.Model.Question;
import com.quiz.question_service.Model.QuestionWrapper;
import com.quiz.question_service.Model.Response;
import com.quiz.question_service.Service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController
{
    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;
    //Getting all the questions
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuesions()
    {
        return questionService.getAllQuestions();
    }
    //search by category
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }
    // adding new question
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);

    }
    @GetMapping("{id}")
    public ResponseEntity<Question> editQuestion(@PathVariable int id)
    {
        //Question existingQuestion = questionService.getQuestionById(id)/// questionService.editQuestion(id);


        return questionService.getQuestionById(id);
    }

    @PostMapping("edit/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id,@RequestBody Question question)
    {
        ResponseEntity<Question> existingQuestion = questionService.getQuestionById(id);
        existingQuestion.getBody().setQuestionTitle(question.getQuestionTitle());
        existingQuestion.getBody().setOption1(question.getOption1());
        existingQuestion.getBody().setOption2(question.getOption2());
        existingQuestion.getBody().setOption3(question.getOption3());
        existingQuestion.getBody().setOption4(question.getOption4());
        existingQuestion.getBody().setRightAnswer(question.getRightAnswer());
        existingQuestion.getBody().setDifficultyLevel(question.getDifficultyLevel());
        existingQuestion.getBody().setCategory(question.getCategory());

        return questionService.updateQuestion(existingQuestion.getBody());
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id)
    {
        return questionService.deleteQuestion(id);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions)
    {
        return questionService.getQuestionForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIds)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionsIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }
//generate
//getQuestions(questionId)
//getScore

}
