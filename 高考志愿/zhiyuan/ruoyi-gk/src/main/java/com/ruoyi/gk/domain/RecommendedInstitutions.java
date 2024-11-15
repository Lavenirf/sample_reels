package com.ruoyi.gk.domain;

import java.math.BigDecimal;
import java.util.List;

public class RecommendedInstitutions {
     private Long id;
     private String name;
     private double AcceptanceRate;
     private int number;
     private int userId;
     private double Recommendation;
     private List<SchoolScores> scores;

     public double getAcceptanceRate() {
          return AcceptanceRate;
     }

     public double getRecommendation() {
          return Recommendation;
     }

     public void setRecommendation(double recommendation) {
          Recommendation = recommendation;
     }

     public int getUserId() {
          return userId;
     }

     public void setUserId(int userId) {
          this.userId = userId;
     }



     public void setAcceptanceRate(double acceptanceRate) {
          AcceptanceRate = acceptanceRate;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public List<SchoolScores> getScores() {
          return scores;
     }

     public void setScores(List<SchoolScores> scores) {
          this.scores = scores;
     }

     public int getNumber() {
          return number;
     }

     public void setNumber(int number) {
          this.number = number;
     }

     @Override
     public String toString() {
          return "RecommendedInstitutions{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", AcceptanceRate=" + AcceptanceRate +
                  ", number=" + number +
                  ", userId=" + userId +
                  ", Recommendation=" + Recommendation +
                  ", scores=" + scores +
                  '}';
     }
}
