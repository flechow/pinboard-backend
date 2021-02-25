package com.flechow.pinboard.web.rest;

import com.flechow.pinboard.domain.Image;
import com.flechow.pinboard.domain.LatLng;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class OfferDto {

    private Double price;
    private LocalDate publishDate;
    private String address;
    private String title;
    private String owner;
    private String description;
    private Float viewedCounter;
    private String category;
    private LatLng location;
    private Set<Image> images = new HashSet<>();

    public OfferDto() {
    }

    public OfferDto(Double price, LocalDate publishDate, String address, String title, String owner, String description, Float viewedCounter, String category, LatLng latLng, Set<Image> images) {
        this.price = price;
        this.publishDate = publishDate;
        this.address = address;
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.viewedCounter = viewedCounter;
        this.category = category;
        this.location = latLng;
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getViewedCounter() {
        return viewedCounter;
    }

    public void setViewedCounter(Float viewedCounter) {
        this.viewedCounter = viewedCounter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
