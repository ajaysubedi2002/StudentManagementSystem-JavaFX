package com.example.studentmanagementsystem;

import javafx.beans.property.SimpleStringProperty;

public class Users {
    private SimpleStringProperty userId;
    private SimpleStringProperty userEmail;
    private SimpleStringProperty userName;
    private SimpleStringProperty userGender;
    private SimpleStringProperty userRole;

    public Users(String userId, String userEmail, String userName, String userGender, String userRole) {
        this.userId = new SimpleStringProperty(userId);
        this.userEmail = new SimpleStringProperty(userEmail);
        this.userName = new SimpleStringProperty(userName);
        this.userGender = new SimpleStringProperty(userGender);
        this.userRole = new SimpleStringProperty(userRole);
    }

    public String getUserId() {
        return userId.get();
    }

    public SimpleStringProperty userIdProperty() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail.get();
    }

    public SimpleStringProperty userEmailProperty() {
        return userEmail;
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public String getUserGender() {
        return userGender.get();
    }

    public SimpleStringProperty userGenderProperty() {
        return userGender;
    }

    public String getUserRole() {
        return userRole.get();
    }

    public SimpleStringProperty userRoleProperty() {
        return userRole;
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public void setUserEmail(String userEmail) {
        this.userEmail.set(userEmail);
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setUserGender(String userGender) {
        this.userGender.set(userGender);
    }

    public void setUserRole(String userRole) {
        this.userRole.set(userRole);
    }
}
