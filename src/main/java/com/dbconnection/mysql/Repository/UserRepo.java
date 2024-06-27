package com.dbconnection.mysql.Repository;

import com.dbconnection.mysql.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPassword(String password);

    boolean findByEmailAndPassword(String email,String password);
}
