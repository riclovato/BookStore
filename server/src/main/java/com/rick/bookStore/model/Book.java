package com.rick.bookStore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Book implements Serializable {
    private Long id;
    private String author;
    private String title;
    private Date launchDate;
    private Double price;

    public Book() {
    }

    public Book(String author, String title, Date launchDate, Double price) {
        this.author = author;
        this.title = title;
        this.launchDate = launchDate;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(launchDate, book.launchDate) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, launchDate, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", launchDate=" + launchDate +
                ", price=" + price +
                '}';
    }
}
