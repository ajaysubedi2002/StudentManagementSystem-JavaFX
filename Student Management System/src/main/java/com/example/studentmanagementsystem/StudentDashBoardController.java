package com.example.studentmanagementsystem;
import static com.example.studentmanagementsystem.LogOut.logOut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import static com.example.studentmanagementsystem.Uses.changeScene;
import java.io.IOException;


public class StudentDashBoardController {

    @FXML
    protected void goToProblemPg(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "ReportProblem_Form.fxml", "Problem Report");
        }

    @FXML
    protected void goToQuizPg(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "QuizesAndSurveys_Form.fxml", "Published Quiz");
    }

    @FXML
    protected void goToMaterialPg(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "CourseMaterialAll.fxml", "Course Materials");
    }

    @FXML
    protected void goToExtraCurricular(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "ParticipateInSport.fxml", "Register for extra-curricular activities");
    }

    @FXML
    public static void goToStdDashboard(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "StudentDashboard.fxml", "Student's Dashboard");
    }
    @FXML
    protected void LogOut(ActionEvent actionEvent) throws IOException {
        logOut(actionEvent);
    }

    }
