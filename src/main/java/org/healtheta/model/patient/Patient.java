package org.healtheta.model.patient;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import org.healtheta.model.common.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Patient")
public class Patient implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(unique=true, name = "_identifier")
    private Identifier identifier;

    @Column(name = "_active")
    private boolean active;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_name")
    private HumanName name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_telecom")
    private List<ContactPoint> telecom;

    @Column(name = "_gender")
    private String gender;

    @Column(name = "_birthDate")
    private Date birthDate;

    @Column(name = "_deceased")
    private boolean deceased;

    @Column(name = "_deceasedDateTime")
    private Date deceasedDateTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_address")
    private List<Address> address;

    @Column(name = "_maritialStatus")
    private boolean maritialStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_photo")
    private Attachment photo;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_contact")
    private PatientContact contact;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "_communication")
    private List<PatientCommunication> communication;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_generalPractitioner")
    private Reference generalPractitioner;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_managingOrganization")
    private Reference managingOrganization;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "_link")
    private PatientLink link;

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setName(HumanName name) {
        this.name = name;
    }

    public void setTelecom(List<ContactPoint> telecom) {
        this.telecom = telecom;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }

    public void setDeceasedDateTime(Date deceasedDateTime) {
        this.deceasedDateTime = deceasedDateTime;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setMaritialStatus(boolean maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public void setPhoto(Attachment photo) {
        this.photo = photo;
    }

    public void setContact(PatientContact contact) {
        this.contact = contact;
    }

    public void setCommunication(List<PatientCommunication> communication) {
        this.communication = communication;
    }

    public void setGeneralPractitioner(Reference generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    public void setManagingOrganization(Reference managingOrganization) {
        this.managingOrganization = managingOrganization;
    }

    public void setLink(PatientLink link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public boolean isActive() {
        return active;
    }

    public HumanName getName() {
        return name;
    }

    public List<ContactPoint> getTelecom() {
        return telecom;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public Date getDeceasedDateTime() {
        return deceasedDateTime;
    }

    public List<Address> getAddress() {
        return address;
    }

    public boolean isMaritialStatus() {
        return maritialStatus;
    }

    public Attachment getPhoto() {
        return photo;
    }

    public PatientContact getContact() {
        return contact;
    }

    public List<PatientCommunication> getCommunication() {
        return communication;
    }

    public Reference getGeneralPractitioner() {
        return generalPractitioner;
    }

    public Reference getManagingOrganization() {
        return managingOrganization;
    }

    public PatientLink getLink() {
        return link;
    }
}

