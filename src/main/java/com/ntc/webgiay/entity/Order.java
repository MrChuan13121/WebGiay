package com.ntc.webgiay.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "note", columnDefinition = "TEXT")
    private String note;

    @Column( name = "quantity")
    private int quantity;

    @Column( name = "address_receiver",nullable = false)
    private String addressReceiver;

    @Column( name = "name_receiver", nullable = false, length = 128)
    private String nameReceiver;

    @Column( name = "phone_number_receiver", nullable = false, length = 15)
    private String phoneNumberReceiver;

    @Column(name = "price")
    private float price;

    @Column( name = "status",columnDefinition = "BOOLEAN")
    private boolean status;

    @Column( name = "created_at")
    private Timestamp createdAt;

    @Column( name = "modified_at")
    private  Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany( mappedBy = "likedOrders")
    private Set<Product> likes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddressReceiver() {
        return addressReceiver;
    }

    public void setAddressReceiver(String addressReceiver) {
        this.addressReceiver = addressReceiver;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public String getPhoneNumberReceiver() {
        return phoneNumberReceiver;
    }

    public void setPhoneNumberReceiver(String phoneNumberReceiver) {
        this.phoneNumberReceiver = phoneNumberReceiver;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
