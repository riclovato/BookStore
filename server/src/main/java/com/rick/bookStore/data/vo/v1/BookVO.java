package com.rick.bookStore.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookVO implements Serializable {
    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String author;
    private String title;
    private Date launchDate;
    private double price;


    public BookVO() {

    }

    public BookVO(Long key, String author, Date launchDate, double price, String title) {
        this.key = key;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        BookVO bookVO = (BookVO) o;
        return Double.compare(bookVO.price, price) == 0 && Objects.equals(key, bookVO.key)
                && Objects.equals(author, bookVO.author) && Objects.equals(launchDate, bookVO.launchDate)
                && Objects.equals(title, bookVO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, author, launchDate, price, title);
    }
}
