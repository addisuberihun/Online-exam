package com.dbconnection.mysql.Service;

import com.dbconnection.mysql.Model.User;
import com.dbconnection.mysql.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public boolean isEmailExists(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        return user.isPresent();
    }
    public String userRegistration(User user){
        userRepo.save(user);
        return "registered Succesfully";

    }
    public String userLogin(User user){
        return "hello";

    }

    public boolean isPasswordEqual(String password,String confPassword) {
        return password.equals(confPassword);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public User getById(Integer id) {
       return userRepo.getById(id);
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    public User editUser(User user) {
        return userRepo.save(user);
    }


    public boolean isEmailAndPasswordExists(String email,String password) {
        return userRepo.findByEmailAndPassword(email,password);
    }
}
