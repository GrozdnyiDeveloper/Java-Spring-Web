package com.example.dbproject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity()
public class Discount_Card {

    @Id
    private int id;
    @NotNull(message = "Номер карты должен быть указан")
    @Min(value = 10000, message = "Номер карты должен начинаться с 10001")
    private int number;
    @NotNull(message = "У карты должна быть указана скидка")
    private int discount;
    @OneToOne(optional = true, mappedBy = "discountCard")
    private Buyer owner;

    public Discount_Card(){}

    public Discount_Card(int id, int number, int discount, Buyer owner) {
        this.id = id;
        this.number = number;
        this.discount = discount;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Buyer getOwner() {
        return owner;
    }

    public void setOwner(Buyer owner) {
        this.owner = owner;
    }
}
