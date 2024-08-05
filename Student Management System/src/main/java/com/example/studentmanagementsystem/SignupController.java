package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class SignupController implements Initializable {
    @FXML
    private TextField Email, Password, FirstName, LastName, Phone;
    @FXML
    private ChoiceBox<String> Gender;
    private String[] Genders = {"Male", "Female", "Others"};

    @FXML
    protected void onSignup(ActionEvent actionEvent) throws IOException {
        String inputEmail = Email.getText();
        String inputPassword = Password.getText();
        String inputName = FirstName.getText() + " " + LastName.getText();
        String inputPhone = Phone.getText();
        String inputGender = Gender.getValue();

        // Validate inputs
        if (!validateInputs(inputEmail, inputPassword, FirstName.getText(), LastName.getText(), inputPhone, inputGender)) {
            return;
        }

        // Generate salt and hash the password
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(inputPassword, salt);

        String userId = generateUserId();

        // Save to CSV only if inputs are valid
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter("Users.csv", true))) {
            csvWriter.write(userId + "," + inputEmail + "," + hashedPassword + "," + salt + "," + inputName + "," + inputGender + ",Student");
            csvWriter.newLine();
        }

        // Redirect to login page after successful signup
        try {
            changeScene(actionEvent, "StudentDashboard.fxml", "Log in");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void BackBtn(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "Initials.fxml", "Initials");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Gender.getItems().addAll(Genders);
    }

    private String generateUserId() {
        int highestId = 0;
        try (BufferedReader csvReader = new BufferedReader(new FileReader("Users.csv"))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length > 0 && data[0].startsWith("K") && data[0].length() == 4) {
                    try {
                        int idNum = Integer.parseInt(data[0].substring(1));
                        if (idNum > highestId) {
                            highestId = idNum;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid user ID format: " + data[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Users.csv: " + e.getMessage());
        }

        return String.format("K%03d", highestId + 1);
    }

    private boolean validateInputs(String email, String password, String firstName, String lastName, String phone, String gender) {
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || gender == null) {
            showAlert(AlertType.ERROR, "Form Error!", "Please fill in all the fields.");
            return false;
        }

        if (!isValidEmail(email)) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter a valid email address.");
            return false;
        }

        if (password.length() < 8) {
            showAlert(AlertType.ERROR, "Form Error!", "Password must be at least 8 characters long.");
            return false;
        }

        if (!isValidPhoneNumber(phone)) {
            showAlert(AlertType.ERROR, "Form Error!", "Please enter a valid phone number.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        String phoneRegex = "\\d{10}";
        return phone.matches(phoneRegex);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}