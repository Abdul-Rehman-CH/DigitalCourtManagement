package com.court.digitalcourtmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "judges")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
public class Judge extends User {

    private String judgeName;
    private String courtRoom;

    @OneToMany(mappedBy = "judgeAssigned")
    private List<CourtCase> cases = new ArrayList<>();

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

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