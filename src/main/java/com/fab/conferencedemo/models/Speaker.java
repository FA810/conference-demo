package com.fab.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity(name = "speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Speaker {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long speakerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "speaker_bio")
    private String speakerBio;

    @Column(name = "speaker_photo")
    @Lob // large object
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] speakerPhoto;

    // avoid loops over many-to-many relationships
    @JsonIgnore
    @ManyToMany(mappedBy = "speakers")
    private List<Session> session_speakers;

    public Speaker(){
    }

    public Long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Long speakerId) {
        this.speakerId = speakerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeakerBio() {
        return speakerBio;
    }

    public void setSpeakerBio(String speakerBio) {
        this.speakerBio = speakerBio;
    }

    @JsonIgnore
    public List<Session> getSessions() {
        return session_speakers;
    }

    public void setSessions(List<Session> sessions) {
        this.session_speakers = sessions;
    }

    public byte[] getSpeakerPhoto() {
        return speakerPhoto;
    }

    public void setSpeakerPhoto(byte[] speakerPhoto) {
        this.speakerPhoto = speakerPhoto;
    }
}

