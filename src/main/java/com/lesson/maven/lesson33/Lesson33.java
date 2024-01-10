package com.lesson.maven.lesson33;

import java.time.LocalDate;
import java.util.UUID;

public class Lesson33 {
    public static final String CONNECTION_STR =
            "jdbc:postgresql://localhost:5432/lessons";
    public static final String LOGIN = "fnx";
    public static final String PWD = "spb1227fenjs";

    public static void main(String[] args) {

        // установить соединение
        // отправить запрос
        // закрыть соединение

        // public Author(String uniqueName, LocalDate registeredAt, boolean isActive)
        AuthorQuery authorQuery = new AuthorQuery();
        authorQuery.createTable();
        Author author = new Author(UUID.randomUUID().toString());
        authorQuery.insertIntoTable(author);
        System.out.println(authorQuery.getAllAuthors());
        System.out.println(authorQuery.getActiveAuthorsByRegisteredAt(LocalDate.now().plusMonths(1)));

        author.setActive(false);
        System.out.println(authorQuery.update(author));
    }
}