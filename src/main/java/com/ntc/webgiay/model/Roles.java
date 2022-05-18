package com.ntc.webgiay.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "roles_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany( mappedBy = "roles")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Roles(){
        this.id = 1;
        this.name = "USER";
        this.id = 2;
        this.name = "ADMIN";
    }



}


