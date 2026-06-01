package com.court.digitalcourtmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
public class Client extends User {

    private String CNICNumber;

    @Lob
    private byte[] cnicFrontImage;

    @Lob
    private byte[] cnicBackImage;

    @OneToMany(mappedBy = "client")
    private List<CourtCase> cases = new ArrayList<>();

    public String getCnicNumber() {
        return CNICNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.CNICNumber = cnicNumber;
    }

    public byte[] getCnicFrontImage() {
        return cnicFrontImage;
    }

    public void setCnicFrontImage(byte[] cnicFrontImage) {
        this.cnicFrontImage = cnicFrontImage;
    }

    public byte[] getCnicBackImage() {
        return cnicBackImage;
    }

    public void setCnicBackImage(byte[] cnicBackImage) {
        this.cnicBackImage = cnicBackImage;
    }

    public List<CourtCase> getCases() {
        return cases;
    }

    public void setCases(List<CourtCase> cases) {
        this.cases = cases;
    }
}