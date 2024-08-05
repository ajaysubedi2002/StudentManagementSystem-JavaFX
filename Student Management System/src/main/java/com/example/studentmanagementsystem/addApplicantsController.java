package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class addApplicantsController {
    @FXML
    private TextField Email, Name;

    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AdmissionOfficerDashboard.fxml", "AdmissionOfficer's Dashboard");
    }

    @FXML
    protected void addApplicants() {
        String email = Email.getText();
        String name = Name.getText();
        String applicantId = generateApplicantId();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Applicants.csv", true))) {
            writer.write(applicantId + "," + name + "," + email);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateApplicantId() {
        int highestId = 0;
        try (BufferedReader csvReader = new BufferedReader(new FileReader("Applicants.csv"))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length > 0 && data[0].startsWith("D") && data[0].length() == 4) {
                    try {
                        int idNum = Integer.parseInt(data[0].substring(1));
                        if (idNum > highestId) {
                            highestId = idNum;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Applicant ID format: " + data[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Applicants.csv: " + e.getMessage());
        }

        return String.format("D%03d", highestId + 1);
    }
}
