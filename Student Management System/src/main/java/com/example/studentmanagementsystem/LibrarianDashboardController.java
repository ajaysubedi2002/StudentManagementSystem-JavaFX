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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.studentmanagementsystem.LogOut.logOut;
import static com.example.studentmanagementsystem.Uses.changeScene;

public class LibrarianDashboardController implements Initializable {

    @FXML
    private TableView<Books> BooksTable;
    @FXML
    private TableColumn<Books, String> BookId;
    @FXML
    private TableColumn<Books, String> Title;
    @FXML
    private TableColumn<Books, String> Author;
    @FXML
    private TableColumn<Books, String> Genre;
    @FXML
    private TableColumn<Books, String> Status;
    @FXML
    private TableColumn<Books, Void> delete;

    private ObservableList<Books> bookObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        Title.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        Author.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        Genre.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        Status.setCellValueFactory(new PropertyValueFactory<>("bookStatus"));
        Status.setCellFactory(column -> new TableCell<Books, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setOnMouseClicked(event -> {
                        Books book = getTableView().getItems().get(getIndex());
                        String newStatus = item.equals("Available") ? "Unavailable" : "Available";
                        book.setBookStatus(newStatus);
                        setText(newStatus);
                        updateCSV();
                    });
                }
            }
        });

        addDeleteButtonToTable();

        loadBookDataFromCSV("Books.csv");

        BooksTable.setItems(bookObservableList);
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<Books, Void>, TableCell<Books, Void>> cellFactory = new Callback<TableColumn<Books, Void>, TableCell<Books, Void>>() {
            @Override
            public TableCell<Books, Void> call(final TableColumn<Books, Void> param) {
                final TableCell<Books, Void> cell = new TableCell<Books, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((event) -> {
                            Books book = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(book);
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

    private void loadBookDataFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 5) {
                    Books book = new Books(details[0].trim(), details[1].trim(), details[2].trim(), details[3].trim(), details[4].trim());
                    bookObservableList.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCSV() {
        List<Books> books = new ArrayList<>(BooksTable.getItems());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Books.csv"))) {
            for (Books book : books) {
                writer.write(book.getBookId() + "," + book.getBookTitle() + "," + book.getBookAuthor() + "," + book.getBookGenre() + "," + book.getBookStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void goToAddBooks(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "addBooks.fxml", "Librarian's Dashboard");
    }

    @FXML
    protected void LogOut(ActionEvent actionEvent) throws IOException {
        logOut(actionEvent);
    }
}
