package com.lesson.maven.jpa;

import jakarta.persistence.*;

//@MappedSuperclass родитель не является entity классом

@Entity //родитель является entity классом
//@Inheritance (strategy = InheritanceType.SINGLE_TABLE) одна таблица на всех
//@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS) // для каждого таблица
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_users")
public class User {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
