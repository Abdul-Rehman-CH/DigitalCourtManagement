package com.court.digitalcourtmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lawyers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Lawyer extends BaseUser {

    private String barNumber;
    private String specialization;

    @OneToMany(mappedBy = "lawyerAssigned", fetch = FetchType.LAZY)
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
