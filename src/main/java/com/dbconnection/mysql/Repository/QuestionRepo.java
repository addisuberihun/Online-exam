package com.dbconnection.mysql.Repository;

import com.dbconnection.mysql.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepo extends JpaRepository<Question,Integer>{
    List<Question> findByCategory(String category);


    Question findByid(Integer id);
}
