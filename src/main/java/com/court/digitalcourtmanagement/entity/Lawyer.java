package com.court.digitalcourtmanagement.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lawyers")
public class Lawyer extends User {

    private String LawyerName;
    private String Specialization;
    private String BarCouncilNumber;

    @OneToMany(mappedBy = "lawyerAssigned")
    private List<CourtCase> casesAssigned;

    public String getLawyerName() {
        return LawyerName;
    }

    public void setLawyerName(String lawyerName) {
        this.LawyerName = lawyerName;
    }

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