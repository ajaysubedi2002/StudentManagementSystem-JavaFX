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

public class ViewParticipantController {

    @FXML
    private TableView<Participant> participantTable;
    @FXML
    private TableColumn<Participant, String> idColumn;
    @FXML
    private TableColumn<Participant, String> activityColumn;

    @FXML
    private void initialize() {
        // Initialize the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));

        // Load data from CSV file
        loadDataFromCSV("Participants.csv");
    }

    private void loadDataFromCSV(String filePath) {
        ObservableList<Participant> participants = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    participants.add(new Participant(data[0], data[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        participantTable.setItems(participants);
    }
    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AdminDashboard.fxml", "Admins Dashboard");
    }
}
