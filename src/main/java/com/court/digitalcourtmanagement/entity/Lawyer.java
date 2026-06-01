package com.court.digitalcourtmanagement.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lawyers")
public class Lawyer extends User {
    private String Specialization;
    private String BarCouncilNumber;

    @OneToMany(mappedBy = "lawyerAssigned")
    private List<CourtCase> casesAssigned;



    public String getSpec() {
        return Specialization;
    }

    public void setSpec(String specialization) {
        this.Specialization = specialization;
    }

    public String getBarCNum() {
        return BarCouncilNumber;
    }

    public void setBarCNum(String barCouncilNumber) {
        this.BarCouncilNumber = barCouncilNumber;
    }

    public List<CourtCase> getCasesAssigned() {
        return casesAssigned;
    }

    public void setCasesAssigned(List<CourtCase> casesAssigned) {
        this.casesAssigned = casesAssigned;
    }
}