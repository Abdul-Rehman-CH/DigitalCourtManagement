package com.court.digitalcourtmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "lawyers")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
public class Lawyer extends User {

    private String barNumber,specialization;

    @OneToMany(mappedBy = "lawyerAssigned")
    @JsonIgnoreProperties({"judgeAssigned", "lawyerAssigned", "client"})
    private List<CourtCase> cases = new ArrayList<>();

    public String getBarNumber() {
        return barNumber;
    }

    public void setBarNumber(String barNumber) {
        this.barNumber = barNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<CourtCase> getCases() {
        return cases;
    }

    public void setCases(List<CourtCase> cases) {
        this.cases = cases;
    }
}