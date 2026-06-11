package com.court.digitalcourtmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "judges")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Judge extends BaseUser {

    private String courtRoom;

 
    @OneToMany(mappedBy = "judgeAssigned", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"judgeAssigned", "lawyerAssigned", "client"})
    private List<CourtCase> cases = new ArrayList<>();

    public String getCourtRoom() {
        return courtRoom;
    }

    public void setCourtRoom(String courtRoom) {
        this.courtRoom = courtRoom;
    }

    public List<CourtCase> getCases() {
        return cases;
    }

    public void setCases(List<CourtCase> cases) {
        this.cases = cases;
    }
}
