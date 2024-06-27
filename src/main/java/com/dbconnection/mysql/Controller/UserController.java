package com.dbconnection.mysql.Controller;

import com.dbconnection.mysql.Model.*;
import com.dbconnection.mysql.Service.QuestionService;
import com.dbconnection.mysql.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    Result result;

    Boolean submitted = false;


    @GetMapping("/home")
    public String home(){
        return "static/index";
    }

    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }


    @GetMapping("/register")
    public String register(){

        return "registrationPage";
    }



@PostMapping("login")
    public ModelAndView login(User user,Model model){
    ModelAndView modelAndView = new ModelAndView();
        if(userService.isEmailExists(user.getEmail()) ){

            model.addAttribute("email",user.getEmail());
            modelAndView.setViewName("studentPage");
            return modelAndView;
        }else{
            model.addAttribute("error","Email doesnt exist");
            modelAndView.setViewName("loginPage");
            return modelAndView;

        }

    }





    @PostMapping("/register")
    public ModelAndView register(User user,String confpassword,Model model) {
        ModelAndView modelAndView = new ModelAndView();
         if (userService.isEmailExists(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            modelAndView.setViewName("registrationPage");
            return modelAndView;}

        if (!userService.isPasswordEqual(user.getPassword(),confpassword)) {
            model.addAttribute("error", "Password Doesnt match!");
            modelAndView.setViewName("registrationPage");
            return modelAndView;
        }
        else{
            userService.userRegistration(user);
            modelAndView.setViewName("loginPage");
            return modelAndView;

        }


    }


    @GetMapping("/quiz")
    public String quiz(Model m) {
        QuestionForm qForm = questionService.getQuestions();
        m.addAttribute("qForm", qForm);

        return "examPage";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
        if(!submitted) {
            result.setTotalCorrect(questionService.getResult(qForm));
            questionService.saveScore(result);
            submitted = true;
        }

        return "result";
    }


}

