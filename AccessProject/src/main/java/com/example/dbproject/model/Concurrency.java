package com.example.dbproject.model;

import javax.persistence.*;
import java.util.Collection;

@Entity()
public class Concurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Staff staff;
    @OneToMany (mappedBy = "concurrency", fetch = FetchType.EAGER)
    private Collection<Orderr> tenants;

    public Concurrency(){}

    public Concurrency(int id, Staff staff, Collection<Orderr> tenants) {
        this.id = id;
        this.staff = staff;
        this.tenants = tenants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Collection<Orderr> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Orderr> tenants) {
        this.tenants = tenants;
    }
}
