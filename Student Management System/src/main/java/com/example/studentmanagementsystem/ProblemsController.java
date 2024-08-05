package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class ProblemsController {

    @FXML
    private TableView<Problem> problemsTable;
    @FXML
    private TableColumn<Problem, String> idColumn;
    @FXML
    private TableColumn<Problem, String> titleColumn;
    @FXML
    private TableColumn<Problem, String> messageColumn;

    @FXML
    public void initialize() {
        // Set up columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        // Load data
        loadData();
    }

    private void loadData() {
        ObservableList<Problem> problems = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader("Problems/Problems.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 3) {
                    problems.add(new Problem(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        problemsTable.setItems(problems);
    }
    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AdminDashboard.fxml", "Admin's Dashboard");
    }
}
