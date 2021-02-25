package com.flechow.pinboard.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Offer.
 */
@Entity
@Table(name = "offer")
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "location")
    private String location;

    @Column(name = "address")
    private String address;

    @Column(name = "title")
    private String title;

    @Column(name = "owner")
    private String owner;

    @Column(name = "description")
    private String description;

    @Column(name = "viewed_counter")
    private Float viewedCounter;

    @OneToOne
    @JoinColumn(unique = true)
    private Category category;

    @OneToOne
    @JoinColumn(unique = true)
    private LatLng latLng;

    @OneToMany(mappedBy = "offer")
    private Set<Image> images = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public Offer price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Offer publishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getLocation() {
        return location;
    }

    public Offer location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public Offer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public Offer title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public Offer owner(String owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public Offer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getViewedCounter() {
        return viewedCounter;
    }

    public Offer viewedCounter(Float viewedCounter) {
        this.viewedCounter = viewedCounter;
        return this;
    }

    public void setViewedCounter(Float viewedCounter) {
        this.viewedCounter = viewedCounter;
    }

    public Category getCategory() {
        return category;
    }

    public Offer category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public Offer latLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Offer images(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Offer addImage(Image image) {
        this.images.add(image);
        image.setOffer(this);
        return this;
    }

    public Offer removeImage(Image image) {
        this.images.remove(image);
        image.setOffer(null);
        return this;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        return id != null && id.equals(((Offer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Offer{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", publishDate='" + getPublishDate() + "'" +
            ", location='" + getLocation() + "'" +
            ", address='" + getAddress() + "'" +
            ", title='" + getTitle() + "'" +
            ", owner='" + getOwner() + "'" +
            ", description='" + getDescription() + "'" +
            ", viewedCounter=" + getViewedCounter() +
            "}";
    }
}
