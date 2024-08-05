package com.example.studentmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class ParticipateInSportController {

    @FXML
    private ChoiceBox<String> choiceBoxSports;

    @FXML
    private TextField userIdField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        choiceBoxSports.getItems().addAll("Football", "Basketball", "Badminton");
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        String userId = userIdField.getText();
        String selectedSport = choiceBoxSports.getValue();

        if (userId != null && !userId.isEmpty() && selectedSport != null) {
            saveToCSV(userId, selectedSport);
        }
    }

    private void saveToCSV(String userId, String sport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Participants.csv", true))) {
            writer.write(userId + "," + sport);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "StudentDashboard.fxml", "Student's Dashboard");
    }
}
