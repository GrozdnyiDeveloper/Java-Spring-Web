package com.example.dbproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Collection;

@Entity()
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "У акции должно быть название")
    private String name;
    @OneToMany (mappedBy = "event", fetch = FetchType.EAGER)
    private Collection<Product> tenants;

    public Event(){}

    public Event(int id, String name, Collection<Product> tenants) {
        this.id = id;
        this.name = name;
        this.tenants = tenants;
    }

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

    public Collection<Product> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Product> tenants) {
        this.tenants = tenants;
    }
}
