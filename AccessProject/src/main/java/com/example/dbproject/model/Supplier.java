package com.example.dbproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Collection;

@Entity()
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Нужно указать наименование поставщика")
    private String name;
    @OneToMany (mappedBy = "supplier", fetch = FetchType.EAGER)
    private Collection<Product> tenants;

    public Supplier(){}

    public Supplier(int id, String name, Collection<Product> tenants) {
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
