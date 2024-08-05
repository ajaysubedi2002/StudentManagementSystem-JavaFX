package com.example.studentmanagementsystem;

public class Problem {

    private final String id;
    private final String title;
    private final String message;

    public Problem(String id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
