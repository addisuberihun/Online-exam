package com.dbconnection.mysql.Controller;

import com.dbconnection.mysql.Model.Admin;
import com.dbconnection.mysql.Model.Question;
import com.dbconnection.mysql.Model.User;
import com.dbconnection.mysql.Service.AdminService;
import com.dbconnection.mysql.Service.QuestionService;
import com.dbconnection.mysql.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;
    @GetMapping("/adminLogin")
    public String adminLogin(Model model){
        return "adminlogin";
    }
    @GetMapping("/adminregister")
    public String adminRegister(Model model){

        return "adminregister";
    }



    @PostMapping("/adminlogin")
    public String login(Model model ,Admin admin){
        if(adminService.isEmailExists(admin.getEmail()) && adminService.isPasswordExists(admin.getPassword())){
            String name = admin.getName();
            model.addAttribute("name",name);
            return "adminPage";
        }else{
            return "adminlogin";

        }

    }



    @PostMapping("adminregister")
    public String register(@ModelAttribute("user") Admin admin, String confpassword) {
        if(adminService.isEmailExists(admin.getEmail())){
            return "adminregister";
        }
        else{
            adminService.adminRegistration(admin);
            return "adminlogin";

        }

    }


    @GetMapping("listquestions")
    public String getAllQuestions(Model model){
        List<Question> allQuestions = questionService.getAllQuestions();
        model.addAttribute("allQuestions",allQuestions);
        return "listofquestions";
    }
    @GetMapping("liststudents")
    public String getAllStudents(Model model){
        List<User> allUser = userService.getAllUser();
        model.addAttribute("allUser",allUser);
        return "listofStudents";
    }


    @GetMapping("question/allQuestions")
    public String redirect(){
        return "adminPage";
    }


    @GetMapping("edit/{id}")
    public String edituser(@PathVariable Integer id, Model model){
        User user = userService.getById(id);
        model.addAttribute("editUser", user);
        return "editUser";
    }


    @RequestMapping(value = "/delete/{id}",method={RequestMethod.GET,RequestMethod.DELETE})
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:listOfStudents";

    }

    @RequestMapping(value = "/update/{id}",method={RequestMethod.GET,RequestMethod.PUT})
    public String editUser(User user){
        userService.editUser(user);
        return "redirect:listOfStudents";

    }

    @GetMapping("/addQuestion")
    public String addQuestion(){
        return "addQuestion";
    }
}
