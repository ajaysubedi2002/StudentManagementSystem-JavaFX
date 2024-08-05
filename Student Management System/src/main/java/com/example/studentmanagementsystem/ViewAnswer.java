package com.example.studentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class ViewAnswer {

    @FXML
    private TableView<Answers> tableView;

    @FXML
    private TableColumn<Answers, String> question1Column;

    @FXML
    private TableColumn<Answers, String> question2Column;

    @FXML
    private TableColumn<Answers, String> question3Column;

    @FXML
    private TableColumn<Answers, String> studentIDColumn;

    private final ObservableList<Answers> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        question1Column.setCellValueFactory(new PropertyValueFactory<>("question1"));
        question2Column.setCellValueFactory(new PropertyValueFactory<>("question2"));
        question3Column.setCellValueFactory(new PropertyValueFactory<>("question3"));
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        loadCSVData();
        tableView.setItems(data);
    }

    private void loadCSVData() {
        String csvFile = "MCQ/submissions.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] answers = line.split(csvSplitBy);

                // create a new Answers object and add it to the list
                data.add(new Answers(answers[1], answers[2], answers[3], answers[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "TeacherDashboard.fxml", "Teacher's Dashboard");
    }
}
