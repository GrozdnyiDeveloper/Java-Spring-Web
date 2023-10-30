package com.example.dbproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;

@Entity()
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "У категории должно быть указано название")
    private String name;

    @ManyToMany
    @JoinTable (name="compliance",
            joinColumns=@JoinColumn (name="category_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    @OneToMany (mappedBy = "category", fetch = FetchType.EAGER)
    private Collection<Compliance> tenants;

    public Category(){}

    public Category(int id, String name, List<Product> products, Collection<Compliance> tenants) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Collection<Compliance> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Compliance> tenants) {
        this.tenants = tenants;
    }
}
