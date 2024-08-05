    package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.example.studentmanagementsystem.Uses.changeScene;
import java.io.IOException;

public class HelloController {


    @FXML
    protected void GotoLogin(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "LoginPage.fxml", "Log in");
    }
    @FXML
    protected void GotoSignup(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "SignupPage.fxml", "Sign up");
    }
}