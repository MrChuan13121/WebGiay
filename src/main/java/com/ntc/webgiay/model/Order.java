package com.ntc.webgiay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "note", columnDefinition = "TEXT")
    private String note;



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

//    @OneToMany(mappedBy = "order_detail")
//    private List<OrderDetail> orderDetailList;






}
