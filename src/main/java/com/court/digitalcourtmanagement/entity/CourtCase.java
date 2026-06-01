package com.court.digitalcourtmanagement.entity;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cases")
public class CourtCase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CaseId;    
    private String title;
    private String description;
    private String status;
    private LocalDate filingDate;
    @ManyToOne
    @JsonBackReference(value="judge-case")
    private Judge judgeAssigned;
    public Judge getJudge() {
        return this.judgeAssigned;
    }

    public void setJudge(Judge judge) {
        this.judgeAssigned = judge;
    }
    @ManyToOne
    @JsonBackReference(value="lawyer-case")
    private Lawyer lawyerAssigned;

    public Lawyer getLawyer(){
        return this.lawyerAssigned;
    }
    public void setLawyer(Lawyer lawyer){
        this.lawyerAssigned=lawyer;
    }

    @ManyToOne
    @JsonBackReference(value = "client-case")
    private Client client;
        public Client getClient(){
        return this.client;
    }
    public void setClient(Client cl){
        this.client=cl;
    }

    public CourtCase() {
    }

    public Long getCaseId() {
        return CaseId;
    }

    public void setCaseId(Long id) {
        this.CaseId = id;
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


}
