package com.court.digitalcourtmanagement.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "judges")
public class Judge extends User {


    @OneToMany(mappedBy = "judgeAssigned")
    private List<CourtCase> casesAssigned;

    public Judge() {}

    public List<CourtCase> getCasesAssigned() {
        return casesAssigned;
    }

    public void setCasesAssigned(List<CourtCase> casesAssigned) {
        this.casesAssigned = casesAssigned;
    }
}