package com.court.digitalcourtmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="Judges")
public class Judge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long judgeID;
    private String judgeName;
    
    public Judge(){

    }
    public void setJudgeName(String Name){
        this.judgeName=Name;
    }
    public String getJudgeName(){
        return this.judgeName;
    }
    public void setJudgeId(Long id){
        this.judgeID=id;
    }
    public Long getJudgeId(){
        return this.judgeID;
    }
    
}
