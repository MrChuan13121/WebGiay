package com.ntc.webgiay.model;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "roles_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }
}
