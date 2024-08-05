package com.example.studentmanagementsystem;

public class Applicant {
    private String applicantId;
    private String name;
    private String email;

    public Applicant(String applicantId, String name, String email) {
        this.applicantId = applicantId;
        this.name = name;
        this.email = email;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
