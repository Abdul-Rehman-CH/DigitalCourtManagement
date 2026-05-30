package com.court.digitalcourtmanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
public class Client extends User {

    private String CnicNumber;

    @Lob
    private byte[] CnicFrontImage;

    @Lob
    private byte[] CnicBackImage;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference(value = "client-case")
    private List<CourtCase> Cases;

    public Client() {
    }

    public String getCnicNumber() {
        return CnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.CnicNumber = cnicNumber;
    }

    public byte[] getCnicFrontImage() {
        return CnicFrontImage;
    }

    public void setCnicFrontImage(byte[] cnicFrontImage) {
        this.CnicFrontImage = cnicFrontImage;
    }

    public byte[] getCnicBackImage() {
        return CnicBackImage;
    }

    public void setCnicBackImage(byte[] cnicBackImage) {
        this.CnicBackImage = cnicBackImage;
    }

    public List<CourtCase> getCases() {
        return Cases;
    }

    public void setCases(List<CourtCase> cases) {
        this.Cases = cases;
    }
}