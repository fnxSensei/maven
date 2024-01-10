package com.lesson.maven.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User{
    @ManyToMany
    @JoinTable(name = "tb_castomers_prodects", joinColumns = @JoinColumn(name = "customer", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "products"))
    private List<Product> productList = new ArrayList<>();
}
