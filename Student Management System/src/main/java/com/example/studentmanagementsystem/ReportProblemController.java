package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.studentmanagementsystem.StudentDashBoardController.goToStdDashboard;

public class ReportProblemController {
    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private TextField idField;

    public void onSubmit() {
        String id = idField.getText();
        String titleText = title.getText();
        String message = description.getText();

        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter("Problems/Problems.csv", true))) {
            csvWriter.write(String.format("%s, %s, %s", id, titleText, message));
            csvWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to CSV file", e);
        }
    }

    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        goToStdDashboard(actionEvent);
    }
}
