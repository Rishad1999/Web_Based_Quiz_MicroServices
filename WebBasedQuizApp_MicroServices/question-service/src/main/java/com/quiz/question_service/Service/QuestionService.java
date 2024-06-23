package com.quiz.question_service.Service;


import com.quiz.question_service.Model.Question;
import com.quiz.question_service.Model.QuestionWrapper;
import com.quiz.question_service.Model.Response;
import com.quiz.question_service.dao.QuestionDao;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions()

    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
    {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question)
    {

        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    public ResponseEntity<Question> getQuestionById(int id)
    {
        return new ResponseEntity<>(questionDao.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Question> updateQuestion(Question question)
    {
        return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuestion(int id)
    {
        questionDao.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions)
    {
        List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionsIds)
    {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionsIds)
        {
            questions.add(questionDao.findById(id).get());
        }
        for(Question q: questions)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrappers.add(qw);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses)
    {
        int right = 0;

        for(Response response: responses)
        {
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
            {
                right++;
            }

        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

//    public ResponseEntity<String> editQuestion(int id)
//    {
//
//    }
}
