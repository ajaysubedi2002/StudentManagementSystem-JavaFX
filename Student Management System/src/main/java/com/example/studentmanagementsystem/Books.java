package com.example.studentmanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Books {
    private StringProperty bookId;
    private StringProperty bookTitle;
    private StringProperty bookAuthor;
    private StringProperty bookGenre;
    private StringProperty bookStatus;

    public Books(String bookId, String bookTitle, String bookAuthor, String bookGenre, String bookStatus) {
        this.bookId = new SimpleStringProperty(bookId);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.bookAuthor = new SimpleStringProperty(bookAuthor);
        this.bookGenre = new SimpleStringProperty(bookGenre);
        this.bookStatus = new SimpleStringProperty(bookStatus);
    }

    public String getBookId() {
        return bookId.get();
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public StringProperty bookIdProperty() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor.get();
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor.set(bookAuthor);
    }

    public StringProperty bookAuthorProperty() {
        return bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre.get();
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre.set(bookGenre);
    }

    public StringProperty bookGenreProperty() {
        return bookGenre;
    }

    public String getBookStatus() {
        return bookStatus.get();
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus.set(bookStatus);
    }

    public StringProperty bookStatusProperty() {
        return bookStatus;
    }
}
