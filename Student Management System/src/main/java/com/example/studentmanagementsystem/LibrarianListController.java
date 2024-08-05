package com.example.studentmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class LibrarianListController {

    @FXML
    private TableView<Users> tableView;

    @FXML
    private TableColumn<Users, String> idColumn;

    @FXML
    private TableColumn<Users, String> emailColumn;

    @FXML
    private TableColumn<Users, String> nameColumn;

    @FXML
    private TableColumn<Users, String> genderColumn;

    @FXML
    private TableColumn<Users, Void> deleteColumn;

    private ObservableList<Users> userList = FXCollections.observableArrayList();

    public void initialize() {
        readCSV();

        idColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().userEmailProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().userGenderProperty());

        // Make columns editable
        tableView.setEditable(true);
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        genderColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        // Set on edit commit actions
        idColumn.setOnEditCommit(event -> {
            event.getRowValue().setUserId(event.getNewValue());
            writeCSV();
        });
        emailColumn.setOnEditCommit(event -> {
            event.getRowValue().setUserEmail(event.getNewValue());
            writeCSV();
        });
        nameColumn.setOnEditCommit(event -> {
            event.getRowValue().setUserName(event.getNewValue());
            writeCSV();
        });
        genderColumn.setOnEditCommit(event -> {
            event.getRowValue().setUserGender(event.getNewValue());
            writeCSV();
        });

        ObservableList<Users> studentList = FXCollections.observableArrayList();
        for (Users user : userList) {
            if ("Librarian".equals(user.getUserRole())) {
                studentList.add(user);
            }
        }

        addDeleteButtonToTable();

        tableView.setItems(studentList);
    }

    private void readCSV() {
        String csvFile = "Users.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(csvSplitBy);

                if (userData.length == 7) {
                    Users user = new Users(
                            userData[0],
                            userData[1],
                            userData[4],
                            userData[5],
                            userData[6]
                    );

                    userList.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCSV() {
        String csvFile = "Users.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            for (Users user : userList) {
                writer.append(user.getUserId()).append(",");
                writer.append(user.getUserEmail()).append(",");
                writer.append(user.getUserName()).append(",");
                writer.append(user.getUserGender()).append(",");
                writer.append(user.getUserRole()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<Users, Void>, TableCell<Users, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Users, Void> call(final TableColumn<Users, Void> param) {
                final TableCell<Users, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((event) -> {
                            Users user = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(user);
                            userList.remove(user);
                            writeCSV();
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

        deleteColumn.setCellFactory(cellFactory);
    }

    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "AdminDashboard.fxml", "Admin's Dashboard");
    }
}
