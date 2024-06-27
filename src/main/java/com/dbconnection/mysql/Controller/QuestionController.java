package com.dbconnection.mysql.Controller;

import com.dbconnection.mysql.Model.Question;
import com.dbconnection.mysql.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    @GetMapping("/Category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable  String category){
        return questionService.getQuestionsByCategory(category);

    }

    @GetMapping("/addQuestion")
    public String addQuestionPage(){
        return "addQuestion";
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@ModelAttribute Question question){
        return questionService.addQuestion(question);

    }
    @GetMapping("edit/{id}")
    public String editQuestionPage(@PathVariable Integer id, Model model){
        Question question = questionService.getById(id);
        model.addAttribute("editQuestion", question);
        return "editQuestion";
    }


    @RequestMapping(value = "/delete/{id}",method={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);
        return "redirect:/question/allQuestions";

    }
    @RequestMapping(value = "/update/{id}",method={RequestMethod.GET,RequestMethod.PUT})
    public String editQuestion(Question question){
        questionService.editQuestion(question);
        return "redirect:/question/allQuestions";

    }
}
