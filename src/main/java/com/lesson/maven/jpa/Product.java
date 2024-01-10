package com.lesson.maven.jpa;


import jakarta.persistence.*;
import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.List;

//@NamedNativeQueries() SQL
//@NamedQueries() JPQL
@NamedQueries({
        @NamedQuery(name = "get_all", query = "SELECT prod FROM Product prod"),
        @NamedQuery(name = "get_by_title", query = "SELECT prod FROM Product prod WHERE prod.title = :title_param")
})
@Entity //product
@Table(name = "tb_products") //описание таблицы
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(nullable = false, length = 1000) // описание столбца
    private String description;

    //EnumType.ORDINAL;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "provider_name", nullable = false)
    private Provider provider;

    @ManyToMany(mappedBy = "productList")
    private List<Customer> customerList=new ArrayList<>();

    public Product() {}

    public Product(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public enum Category{
        SPORT, HOME, STUDY
    }
}

