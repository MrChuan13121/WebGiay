package com.ntc.webgiay.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "name_category", nullable = false, length = 128)
    private String nameCategory;

    @Column( name = "status",columnDefinition = "BOOLEAN")
    private boolean status;

    @OneToMany( mappedBy = "category")
    private List<Product> products;

    @ManyToOne
    @JoinColumn( name = "brand_id")
    private  Brand brand;

    //GET,SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
