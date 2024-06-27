package com.dbconnection.mysql.Repository;

import com.dbconnection.mysql.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result,Integer> {
}
