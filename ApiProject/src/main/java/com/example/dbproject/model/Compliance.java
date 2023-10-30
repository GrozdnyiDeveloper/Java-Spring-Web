package com.example.dbproject.model;

import javax.persistence.*;

@Entity()
public class Compliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Product product;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Category category;

    public Compliance(){}

    public Compliance(int id, Product product, Category category) {
        this.id = id;
        this.product = product;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
