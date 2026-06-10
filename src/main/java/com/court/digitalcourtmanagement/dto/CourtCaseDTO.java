package com.court.digitalcourtmanagement.dto;

import java.time.LocalDate;

public class CourtCaseDTO {

    private Long caseId;
    private String title;
    private String description;
    private String status;
    private LocalDate filingDate;
    private Long clientId;
    private Long judgeId;
    private Long lawyerId;
    private String clientName;
    private String clientCnic;
    private String judgeName;
    private String lawyerName;

    public CourtCaseDTO() {
    }

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Long judgeId) {
        this.judgeId = judgeId;
    }

    public Long getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(Long lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCnic() {
        return clientCnic;
    }

    public void setClientCnic(String clientCnic) {
        this.clientCnic = clientCnic;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getLawyerName() {
        return lawyerName;
    }

    public void setLawyerName(String lawyerName) {
        this.lawyerName = lawyerName;
    }
}

    
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
        
    
    
        
    
