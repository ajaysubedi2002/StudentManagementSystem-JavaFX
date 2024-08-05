package com.example.studentmanagementsystem;

public class Participant {
    private String id;
    private String activity;

    // Constructor
    public Participant(String id, String activity) {
        this.id = id;
        this.activity = activity;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
