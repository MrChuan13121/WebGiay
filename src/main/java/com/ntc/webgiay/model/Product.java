package com.ntc.webgiay.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "name",nullable = false, length = 128)
    private String name;

    @Column( name = "description", columnDefinition = "TEXT")
    private String description;

    @Column( name = "thumbnail")
    private String thumbnail;

    @Column( name = "price")
    private float price;

    @Column( name = "quantity")
    private  int quantity;

    @Column( name = "status",columnDefinition = "BOOLEAN")
    private  boolean status;

    @Column( name = "created_at")
    private Timestamp createdAt;

    @Column( name = "modified_at")
    private Timestamp modifiedAt;

    @OneToMany( mappedBy = "product")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn( name = "category_id")
    private Category category;



    //GET,SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}

