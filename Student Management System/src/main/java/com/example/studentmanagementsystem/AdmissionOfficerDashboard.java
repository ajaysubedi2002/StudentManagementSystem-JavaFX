package com.example.studentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static com.example.studentmanagementsystem.LogOut.logOut;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class AdmissionOfficerDashboard implements Initializable {

    @FXML
    private TableView<Applicant> ApplicantsTable;
    @FXML
    private TableColumn<Applicant, String> ApplicantId;
    @FXML
    private TableColumn<Applicant, String> Name;
    @FXML
    private TableColumn<Applicant, String> Email;
    @FXML
    private TableColumn<Applicant, Void> delete;

    private ObservableList<Applicant> applicantObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApplicantId.setCellValueFactory(new PropertyValueFactory<>("applicantId"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        addDeleteButtonToTable();
        loadApplicantDataFromCSV("Applicants.csv");
        ApplicantsTable.setItems(applicantObservableList);
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<Applicant, Void>, TableCell<Applicant, Void>> cellFactory = new Callback<TableColumn<Applicant, Void>, TableCell<Applicant, Void>>() {
            @Override
            public TableCell<Applicant, Void> call(final TableColumn<Applicant, Void> param) {
                final TableCell<Applicant, Void> cell = new TableCell<Applicant, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((event) -> {
                            Applicant applicant = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(applicant);
                            updateCSV();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        delete.setCellFactory(cellFactory);
    }

    private void loadApplicantDataFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    Applicant applicant = new Applicant(details[0].trim(), details[1].trim(), details[2].trim());
                    applicantObservableList.add(applicant);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCSV() {
        List<Applicant> applicants = new ArrayList<>(ApplicantsTable.getItems());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Applicants.csv"))) {
            for (Applicant applicant : applicants) {
                writer.write(applicant.getApplicantId() + "," + applicant.getName() + "," + applicant.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void goToAddApplicant(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "addApplicants.fxml", "Add Applicant");
    }
    @FXML
    protected void GoToAnnouncementMake(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "MakeAnnouncements.fxml", "Publish Course Materials");
    }
    @FXML
    protected void LogOut(ActionEvent actionEvent) throws IOException {
        logOut(actionEvent);
    }

}
