package com.dbconnection.mysql.Service;

import com.dbconnection.mysql.Model.Question;
import com.dbconnection.mysql.Model.QuestionForm;
import com.dbconnection.mysql.Model.Result;
import com.dbconnection.mysql.Repository.QuestionRepo;
import com.dbconnection.mysql.Repository.ResultRepo;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    QuestionForm qForm;

    @Autowired
    ResultRepo resultRepo;
    public List<Question> getAllQuestions() {
            return questionRepo.findAll();


    }

    public QuestionForm getQuestions() {
        List<Question> allQues = questionRepo.findAll();
        List<Question> qList = new ArrayList<Question>();

        Random random = new Random();

        for(int i=0; i<3; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        qForm.setQuestions(qList);

        return qForm;
    }

    public int getResult(QuestionForm qForm) {
        int correct = 0;

        for(Question q: qForm.getQuestions())
            if(q.getRightAnswer() == q.getChose())
                correct++;

        return correct;
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question question) {

        try{
            questionRepo.save(question);
            return new ResponseEntity<>("Question", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Faliure",HttpStatus.CREATED);
        }

    }

    public void deleteQuestion(int id) {
        questionRepo.deleteById(id);
    }

    public Question getById(Integer id) {
        return questionRepo.findByid(id);
    }

    public Question editQuestion(Question question) {
        return questionRepo.save(question);
    }

    public void saveScore(Result result) {
        Result saveResult = new Result();

        saveResult.setTotalCorrect(result.getTotalCorrect());
        resultRepo.save(saveResult);
    }
}
