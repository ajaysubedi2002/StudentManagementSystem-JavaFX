package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class QuizController {

    @FXML
    private TextField userIdField;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;
    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        List<String> options1 = Arrays.asList("Encapsulation", "Abstraction", "Inheritance", "Polymorphism");
        choiceBox1.getItems().addAll(options1);

        List<String> options2 = Arrays.asList("Overloading", "Overriding", "Dynamic Binding", "None of the above");
        choiceBox2.getItems().addAll(options2);

        List<String> options3 = Arrays.asList("1, 2, 3", "Only 1", "Only 2", "Only 3");
        choiceBox3.getItems().addAll(options3);
    }

    @FXML
    private void handleSubmit() {
        String userId = userIdField.getText();
        String answer1 = choiceBox1.getValue();
        String answer2 = choiceBox2.getValue();
        String answer3 = choiceBox3.getValue();

        if (userId == null || userId.isEmpty()) {
            System.out.println("User ID is required.");
            return;
        }

        if (answer1 == null || answer2 == null || answer3 == null) {
            System.out.println("Please answer all questions.");
            return;
        }

        String fileName = "MCQ/submissions.csv";

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(userId).append(",")
                    .append(answer1).append(",")
                    .append(answer2).append(",")
                    .append(answer3).append("\n");
            System.out.println("Answers saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void BackBtn(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "StudentDashboard.fxml", "Log in");
    }
}
