package com.example.studentmanagementsystem;
import javafx.event.ActionEvent;

import java.io.IOException;

import static com.example.studentmanagementsystem.Uses.changeScene;
public class LogOut {
    public static void logOut(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "Initials.fxml", "Initials");
    }
}
