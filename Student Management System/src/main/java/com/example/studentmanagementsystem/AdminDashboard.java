package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.example.studentmanagementsystem.LogOut.logOut;
import static com.example.studentmanagementsystem.Uses.changeScene;

public class AdminDashboard {
    @FXML
    protected void LogOut(ActionEvent actionEvent) throws IOException {
        logOut(actionEvent);
    }
    @FXML
    protected void goToStudentList(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "StudentList.fxml", "Students List");
    }
    @FXML
    protected void goToTeacherList(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "TeacherList.fxml", "Students List");
    }

    @FXML
    protected void goToLibrarianList(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "LibrarianList.fxml", "Students List");
    }

    @FXML
    protected void goToAdmissionOfficerList(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AdmissionList.fxml", "Students List");
    }
    @FXML
    protected void goToAddUser(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AddUser.fxml", "Add User");
    }
    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AdminDashboard.fxml", "Admins Dashboard");
    }
    @FXML
    protected void goToViewParticipantController(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "ExtraCurriParticipant.fxml", "Admins Dashboard");
    }
    @FXML
    protected void goToViewProblems(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "ViewProblems.fxml", "Problems");
    }
}
