package com.dbconnection.mysql.Repository;

import com.dbconnection.mysql.Model.Admin;
import com.dbconnection.mysql.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Optional<Admin> findByEmail(String email);

    Optional<Admin> findBypassword(String password);
}
