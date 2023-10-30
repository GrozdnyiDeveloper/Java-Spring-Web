package com.example.dbproject.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity()
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "У позиции корзины должны быть указан статус покупки")
    private boolean status;
    @NotNull(message = "У позиции корзины должно быть указано кол-во товара")
    @Min(value = 1, message = "У позиции корзины должно быть больше 1 единицы товара")
    private int amount;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Buyer buyer;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Compliance compliance;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private Orderr orderr;

    public Cart(){}

    public Cart(int id, boolean status, int amount, Buyer buyer, Compliance compliance, Orderr orderr) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.buyer = buyer;
        this.compliance = compliance;
        this.orderr = orderr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Compliance getCompliance() {
        return compliance;
    }

    public void setCompliance(Compliance compliance) {
        this.compliance = compliance;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Orderr getOrderr() {
        return orderr;
    }

    public void setOrderr(Orderr orderr) {
        this.orderr = orderr;
    }
}
