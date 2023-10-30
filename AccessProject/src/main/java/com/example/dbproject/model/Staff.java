package com.example.dbproject.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity()
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Нужно указать фамилию сотрудника")
    private String lastName;
    @NotBlank(message = "Нужно указать имя сотрудника")
    private String name;
    @Size(min = 0, max = 300)
    private String middleName;
    @NotBlank(message = "Нужно указать пол сотрудника")
    private String gender;
    @Email(message = "Почта должна быть почтой")
    @NotBlank(message = "Нужно указать почту сотрудника")
    private String email;
    @NotBlank(message = "Нужно указать пароль сотрудника")
    private String password;
    @OneToMany (mappedBy = "staff", fetch = FetchType.EAGER)
    private Collection<Concurrency> tenants;
    public Staff(){}

    public Staff(int id, String lastName, String name, String middleName, String gender, String email, String password, Collection<Concurrency> tenants) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.tenants = tenants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middle_name) {
        this.middleName = middle_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Concurrency> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Concurrency> tenants) {
        this.tenants = tenants;
    }
}
