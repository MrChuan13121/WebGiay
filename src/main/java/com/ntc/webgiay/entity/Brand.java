package com.ntc.webgiay.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_brand", nullable = false, length = 128)
    private String nameBrand;

    @Column( name = "description", columnDefinition = "TEXT")
    private String description;

    @Column( name = "thumbnail")
    private String thumbnail;

    @Column( name = "status",columnDefinition = "BOOLEAN")
    private boolean status;

    @OneToMany(mappedBy = "brand")
    private List<Category> Categorys;

    //GET,SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
