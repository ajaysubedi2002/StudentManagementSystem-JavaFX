package com.example.studentmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;

import static com.example.studentmanagementsystem.Uses.changeScene;

public class addBooksController {
    @FXML
    private TextField bookTitle, author, genre;

    @FXML
    protected void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "LibrarianDashboard.fxml", "Librarian's Dashboard");
    }

    @FXML
    protected void addBook() {
        String title = bookTitle.getText();
        String authorName = author.getText();
        String bookGenre = genre.getText();
        String BookId = generateBookId();
        String Status = "Available";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Books.csv", true))) {
            writer.write(BookId + "," + title + "," + authorName + "," + bookGenre + "," + Status);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateBookId() {
        int highestId = 0;
        try (BufferedReader csvReader = new BufferedReader(new FileReader("Books.csv"))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length > 0 && data[0].startsWith("B") && data[0].length() == 4) {
                    try {
                        int idNum = Integer.parseInt(data[0].substring(1));
                        if (idNum > highestId) {
                            highestId = idNum;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Book ID format: " + data[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Books.csv: " + e.getMessage());
        }

        return String.format("B%03d", highestId + 1);
    }
}
