package com.dbconnection.mysql.Model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "results")
public class Result {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private int totalCorrect = 0;

        public Result() {
            super();
        }

        public Result(int id, String username, int totalCorrect) {
            super();
            this.id = id;

            this.totalCorrect = totalCorrect;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public int getTotalCorrect() {
            return totalCorrect;
        }

        public void setTotalCorrect(int totalCorrect) {
            this.totalCorrect = totalCorrect;
        }



}
