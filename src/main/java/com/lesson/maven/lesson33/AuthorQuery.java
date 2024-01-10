package com.lesson.maven.lesson33;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorQuery {
//    private final String uniqueName;
//    private final LocalDate registeredAt;
//    private boolean isActive = true;

    public boolean createTable() {
        // DROP TABLE IF EXISTS tb_authors
        String createSql = "CREATE TABLE IF NOT EXISTS tb_authors(" +
                "unique_name VARCHAR(60) PRIMARY KEY, " +
                "registered_at DATE DEFAULT CURRENT_DATE NOT NULL, " +
                "is_active BOOLEAN DEFAULT TRUE)";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // import java.sql.*
        try (Connection connection = DriverManager.getConnection(
                Lesson33.CONNECTION_STR, Lesson33.LOGIN, Lesson33.PWD
        )) {      // Statement | PreparedStatement
            try (Statement statement = connection.createStatement()) {
                try {
                    // для не SELECT запросов
                    statement.executeUpdate(createSql);
                } catch (SQLException e) {
                    System.out.println("Ошибка синтаксиса, " +
                            "отсутствие или наличие таблицы, " +
                            "разрыв соединения");
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка во время соединения с сервером Postgresql");
            throw new RuntimeException(e);
        }
        return true;
    }

    public int[] insertAllIntoTable(List<Author> authors) {
        String insertSQl = "INSERT INTO tb_authors (unique_name) VALUES(?)";

        try (PreparedStatement statement = C3p0ConnectionsPool.getConnectionFromPool()
                .prepareStatement(insertSQl)) {
            for (Author author : authors) {
                statement.setString(1, author.getUniqueName());
                statement.addBatch();
            }
            int[] result = statement.executeBatch();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertIntoTable(Author author) {
        String insertSQl = "INSERT INTO tb_authors (unique_name) VALUES(?)";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // import java.sql.*
        try (Connection connection = DriverManager.getConnection(
                Lesson33.CONNECTION_STR, Lesson33.LOGIN, Lesson33.PWD
        )) {      // Statement | PreparedStatement
            try (PreparedStatement statement = connection.prepareStatement(insertSQl)) {
                statement.setString(1, author.getUniqueName());
                try {
                    // для не SELECT запросов
                    return statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Ошибка синтаксиса, " +
                            "отсутствие или наличие таблицы, " +
                            "разрыв соединения");
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка во время соединения с сервером Postgresql");
            throw new RuntimeException(e);
        }
    }

    public int update(Author author) {
        String updateSql = "UPDATE tb_authors " +
                "SET is_active = ? WHERE unique_name = ?";
        try (Connection connection = C3p0ConnectionsPool.getConnectionFromPool()) {
            try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
                statement.setBoolean(1, author.isActive());
                statement.setString(2, author.getUniqueName());
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Author> getAllAuthors() {
        String selectSql = "SELECT unique_name, " +
                "registered_at AS registered, is_active " +
                "FROM tb_authors";
        List<Author> authors = new ArrayList<>();
        try (Statement statement = C3p0ConnectionsPool
                .getConnectionFromPool().createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                String uniqueName = resultSet.getString("unique_name");
                LocalDate registeredAt = resultSet.getObject("registered", LocalDate.class);
                boolean isActive = resultSet.getBoolean("is_active");
                Author author = new Author(uniqueName, registeredAt, isActive);
                authors.add(author);
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getActiveAuthorsByRegisteredAt(
            LocalDate localDate
    ) {
        String selectSql = "SELECT unique_name FROM tb_authors " +
                "WHERE is_active = true AND registered_at < ?";
        List<String> strings = new ArrayList<>();
        try (PreparedStatement statement = C3p0ConnectionsPool
                .getConnectionFromPool()
                .prepareStatement(selectSql)) {
            statement.setObject(1, localDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                strings.add(resultSet.getString("unique_name"));
            }
            return strings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}