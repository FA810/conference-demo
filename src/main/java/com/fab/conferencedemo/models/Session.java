package com.fab.conferencedemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity(name="sessions")
public class Session {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long sessionId;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "session_description")
    private String sessionDescription;

    @Column(name = "session_length")
    private Long sessionLength;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public Long getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(Long sessionLength) {
        this.sessionLength = sessionLength;
    }

    public Session(){
    }
}
