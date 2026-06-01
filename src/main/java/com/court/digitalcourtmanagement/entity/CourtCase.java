package com.court.digitalcourtmanagement.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "cases")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "caseId"
)
public class CourtCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;

    private String title;
    private String description;
    private String status;
    private LocalDate filingDate;

    @ManyToOne
    private Judge judgeAssigned;

    @ManyToOne
    private Lawyer lawyerAssigned;
    @ManyToOne
    private Client client;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(LocalDate filingDate) {
        this.filingDate = filingDate;
    }

    public Judge getJudge() {
        return judgeAssigned;
    }

    public void setJudge(Judge judgeAssigned) {
        this.judgeAssigned = judgeAssigned;
    }

    public Lawyer getLawyer() {
        return lawyerAssigned;
    }

    public void setLawyer(Lawyer lawyerAssigned) {
        this.lawyerAssigned = lawyerAssigned;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
