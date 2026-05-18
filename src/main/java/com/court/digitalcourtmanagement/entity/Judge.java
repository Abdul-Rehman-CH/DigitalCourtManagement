package com.court.digitalcourtmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.court.digitalcourtmanagement.entity.CourtCase;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.List;

@Entity
@Table(name="Judges")
public class Judge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long judgeID;
    private String judgeName;
    @OneToMany(mappedBy = "judgeAssigned")
    @JsonManagedReference
    private List<CourtCase> casesAssigned;

    
    public Judge(){

    }
    public List<CourtCase> getCasesAssigned() {
        return casesAssigned;
    }

    public void setCasesAssigned(List<CourtCase> casesAssigned) {
        this.casesAssigned = casesAssigned;
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
