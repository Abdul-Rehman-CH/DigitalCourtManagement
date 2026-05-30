package com.court.digitalcourtmanagement.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "judges")
public class Judge extends User {

    private String judgeName;

    @OneToMany(mappedBy = "judgeAssigned")
    private List<CourtCase> casesAssigned;

    public Judge() {}

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public List<CourtCase> getCasesAssigned() {
        return casesAssigned;
    }

    public void setCasesAssigned(List<CourtCase> casesAssigned) {
        this.casesAssigned = casesAssigned;
    }
}