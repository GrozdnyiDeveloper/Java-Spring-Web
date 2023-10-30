package com.example.dbproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.sql.Date;

@Entity()
public class Orderr {

    @Id
    private int id;
    @NotNull(message = "У заказа должна быть указан номер")
    private int number;
    @NotNull(message = "Нужно указать дату заказа")
    private Date date;
    @NotNull(message = "Нужно указать статус оплаты заказа")
    private boolean payment;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Concurrency concurrency;
    private int status_ID;
    @NotNull(message = "У заказа должна быть указан пункт выдачи")
    private int delivery_ID;
    @OneToOne(optional = true, mappedBy = "orderr")
    private Cart owner;

    public Orderr(){}

    public Orderr(int id, int number, Date date, boolean payment, Concurrency concurrency, int status_ID, int delivery_ID, Cart owner) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.payment = payment;
        this.concurrency = concurrency;
        this.status_ID = status_ID;
        this.delivery_ID = delivery_ID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date_of_making) {
        this.date = date_of_making;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public Concurrency getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Concurrency concurrency) {
        this.concurrency = concurrency;
    }

    public int getStatus_ID() {
        return status_ID;
    }

    public void setStatus_ID(int status_ID) {
        this.status_ID = status_ID;
    }

    public int getDelivery_ID() {
        return delivery_ID;
    }

    public void setDelivery_ID(int delivery_ID) {
        this.delivery_ID = delivery_ID;
    }

    public Cart getOwner() {
        return owner;
    }

    public void setOwner(Cart owner) {
        this.owner = owner;
    }
}
