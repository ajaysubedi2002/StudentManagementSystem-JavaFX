package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import static com.example.studentmanagementsystem.Uses.changeScene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField Email, Password;

    @FXML
    protected void onLogin(ActionEvent actionEvent) throws IOException {
        String inputEmail = Email.getText();
        String inputPassword = Password.getText();

        if (!validateInputs(inputEmail, inputPassword)) {
            return;
        }

        String role = "";

        try (BufferedReader csvReader = new BufferedReader(new FileReader("Users.csv"))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length >= 6 && data[1].equals(inputEmail)) {
                    String storedHashedPassword = data[2];
                    String storedSalt = data[3];
                    if (PasswordUtils.hashPassword(inputPassword, storedSalt).equals(storedHashedPassword)) {
                        role = data[6].trim();
                        break;
                    }
                }
            }
        }

        if (!role.isEmpty()) {
            switch (role) {
                case "Student":
                    changeScene(actionEvent, "StudentDashboard.fxml", "Student's Dashboard");
                    break;
                case "Teacher":
                    changeScene(actionEvent, "TeacherDashboard.fxml", "Teacher's Dashboard");
                    break;
                case "Admin":
                    changeScene(actionEvent, "AdminDashboard.fxml", "Admin's Dashboard");
                    break;
                case "Librarian":
                    changeScene(actionEvent, "LibrarianDashboard.fxml", "Librarian's Dashboard");
                    break;
                default:
                    showAlert(AlertType.ERROR, "Login Error", "Unknown role: " + role);
            }
        } else {
            showAlert(AlertType.ERROR, "Login Error", "Login failed. Incorrect email or password.");
        }
    }

    @FXML
    protected void BackBtn(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "Initials.fxml", "Log in");
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error", "Please enter both email and password.");
            return false;
        }
        return true;
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}