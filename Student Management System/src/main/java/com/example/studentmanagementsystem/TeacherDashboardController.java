package com.example.studentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.studentmanagementsystem.LogOut.logOut;
import static com.example.studentmanagementsystem.Uses.changeScene;

public class TeacherDashboardController implements Initializable {

    @FXML
    private TableView<Users> UsersTable;
    @FXML
    private TableColumn<Users, String> UserId, Email, Username, Gender, Role;

    private ObservableList<Users> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        Email.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        Username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("userGender"));
        Role.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        loadUserDataFromCSV("Users.csv");

        UsersTable.setItems(observableList);
    }

    private void loadUserDataFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 7 && "Student".equals(details[6].trim())) {
                    Users user = new Users(
                            details[0].trim(),
                            details[1].trim(),
                            details[4].trim(), // Use index 4 for username
                            details[5].trim(), // Use index 5 for gender
                            details[6].trim()  // Use index 6 for role
                    );
                    observableList.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void GoToUploadCourse(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "UploadCourseMaterial.fxml", "Publish Course Materials");
    }

    @FXML
    protected void GoToViewAnswer(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "ViewAnswers.fxml", "Publish MCQ");
    }

    @FXML
    protected void LogOut(ActionEvent actionEvent) throws IOException {
        logOut(actionEvent);
    }
}
