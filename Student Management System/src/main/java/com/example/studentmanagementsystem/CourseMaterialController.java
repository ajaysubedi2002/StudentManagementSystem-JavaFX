package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class CourseMaterialController {

    @FXML
    private ListView<String> fileListView;

    @FXML
    public void initialize() {
        loadFiles();
    }

    private void loadFiles() {
        File directory = new File("CourseMaterial");

        if (directory.exists() && directory.isDirectory()) {
            List<String> files = List.of(directory.list())
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());
            fileListView.getItems().addAll(files);

            fileListView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) { // double-click
                    String selectedFile = fileListView.getSelectionModel().getSelectedItem();
                    if (selectedFile != null) {
                        openFile(new File(directory, selectedFile));
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("CourseMaterial directory does not exist.");
            alert.showAndWait();
        }
    }

    private void openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                showErrorAlert("Unable to open the file. Ensure there is a default application set to open PDF files.");
                e.printStackTrace();
            }
        } else {
            showErrorAlert("Desktop operations are not supported on this system.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "StudentDashboard.fxml", "Teacher's Dashboard");
    }
}
