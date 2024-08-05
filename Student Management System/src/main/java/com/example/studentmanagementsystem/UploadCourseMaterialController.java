package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class UploadCourseMaterialController {
    @FXML
    private Text labSingleFile;
    @FXML
    private TextField CourseTitle;

    @FXML
    protected void GoBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "TeacherDashboard.fxml", "Teacher's Dashboard");
    }

    @FXML
    protected void singleFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pdf Files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);
        String title = CourseTitle.getText().trim();

        if (selectedFile != null && !title.isEmpty()) {
            // Display selected file path
            labSingleFile.setText("Selected File: " + selectedFile.getAbsolutePath());

            // Define destination directory
            File destinationDir = new File("CourseMaterial");

            // Create the directory if it doesn't exist
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            // Define destination file with title included in the name
            String newFileName = title;
            File destFile = new File(destinationDir.getAbsolutePath() + File.separator + newFileName);

            try {
                // Copy selected file to destination directory
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Show success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File Saved");
                alert.setHeaderText(null);
                alert.setContentText("File saved successfully to: " + destFile.getAbsolutePath());
                alert.showAndWait();
            } catch (IOException e) {
                // Show error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File Save Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while saving the file.");
                alert.showAndWait();

                e.printStackTrace();
            }
        } else {
            // Show error alert if no file is selected or title is empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a file and enter a course title.");
            alert.showAndWait();
        }
    }
}
