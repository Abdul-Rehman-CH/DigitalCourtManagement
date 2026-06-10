package com.court.digitalcourtmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client extends BaseUser {


    @Column(name = "cnic_number")
    private String cnicNumber;

    @Lob
    @Column(name = "cnic_front_image", columnDefinition = "LONGBLOB")
    private byte[] cnicFrontImage;

    @Lob
    @Column(name = "cnic_back_image", columnDefinition = "LONGBLOB")
    private byte[] cnicBackImage;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"client", "judgeAssigned", "lawyerAssigned"})
    private List<CourtCase> cases = new ArrayList<>();

    public String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.cnicNumber = cnicNumber;
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
