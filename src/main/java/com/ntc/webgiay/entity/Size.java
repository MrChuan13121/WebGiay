package com.ntc.webgiay.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "size")
public class Size {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int Id;

    @Column( name = "number_size", nullable = false)
    private int numberSize;

    @Column( name = "quantity")
    private int quantity;

    @OneToMany( mappedBy = "size")
    private List<Product> products;

    //GET,SET
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNumberSize() {
        return numberSize;
    }

    public void setNumberSize(int numberSize) {
        this.numberSize = numberSize;
    }
}
