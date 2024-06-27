package com.dbconnection.mysql.Service;

import com.dbconnection.mysql.Model.Admin;
import com.dbconnection.mysql.Model.User;
import com.dbconnection.mysql.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;

    public String adminRegistration(Admin admin){
        adminRepo.save(admin);
        return "registered Succesfully";

    }

    public boolean isPasswordEqual(String password, String confpassword) {
        return password.equals(confpassword);
    }

    public boolean isEmailExists(String email) {
        Optional<Admin> admin = adminRepo.findByEmail(email);
        return admin.isPresent();
    }

    public boolean isPasswordExists(String password) {
        Optional<Admin> admin = adminRepo.findBypassword(password);
        return admin.isPresent();
    }
}
