package com.lesson.maven.lesson33;

import java.sql.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

//private long id;
//private String title;
//private String text;
//private ZonedDateTime createdAt;
//private Author author;
public class NoteQuery {
    // DATE setObject/getObject LocalDate
    // TIME setObject/getObject LocalTime
    // TIMESTAMP дата + время без временной зоны LocalDateTime
    // TIMESTAMPTZ дата + время + utc OffsetDateTime
    public boolean createTable(){ // SERIAL - INTEGER
        String createSql = "CREATE TABLE IF NOT EXISTS tb_notes(" +
                "id SERIAL PRIMARY KEY, " +
                "title VARCHAR(100) NOT NULL, " +
                "note_text TEXT NOT NULL, " +
                "created_at TIMESTAMPTZ NOT NULL, " +
                "author_unique_name VARCHAR(60) NOT NULL, " +
                "CONSTRAINT fk_author_notes " +
                "FOREIGN KEY (author_unique_name) " +
                "REFERENCES tb_authors (unique_name))";
        try (Connection connection = C3p0ConnectionsPool
                .getConnectionFromPool()){
            try (Statement statement = connection.createStatement()){
                statement.executeUpdate(createSql);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            // return false;
        }
    }
    //  "id SERIAL PRIMARY KEY, " +
//          "title VARCHAR(100) NOT NULL, " +
//          "note_text TEXT NOT NULL, " +
//          "created_at TIMESTAMPTZ NOT NULL, " +
//          "author_unique_name VARCHAR(60) NOT NULL, " +
    public Note insertToTable(Note note){
        String insertSql = "INSERT INTO " +
                "tb_notes(title, note_text, created_at, author_unique_name) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = C3p0ConnectionsPool
                .getConnectionFromPool()
                .prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getText());
            statement.setObject(3, note.getCreatedAt());
            statement.setString(4, note.getAuthor().getUniqueName());
            statement.executeUpdate();
            try (ResultSet key = statement.getGeneratedKeys()){
                if (!key.next()) throw new RuntimeException("Key error");
                note.setId(key.getInt(1));
            }
            return note;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // %символы%
    // %символы
    // символы%
    // ASC
    // unique_name id  title author_name

    // jj          null null null
    public List<Note> getNotesByTitle(String title){
        String selectSqlLeftJoin = "SELECT tb_authors.unique_name " +
                "FROM tb_authors " +
                "LEFT JOIN tb_notes " +
                "ON tb_authors.unique_name = tb_notes.author_unique_name " +
                "WHERE tb_notes.author_unique_name IS NULL" +
                "ORDER BY tb_authors.registered_at DESC";

        String selectSqlJoin = "SELECT tb_notes.*, tb_authors.* " +
                "FROM tb_notes " +
                "JOIN tb_authors " +
                "ON tb_authors.unique_name = tb_notes.author_unique_name " +
                "WHERE tb_notes.title ILIKE ? " +
                "ORDER BY tb_notes.created_at DESC";

        String selectSql = "SELECT tb_notes.*, tb_authors.* " +
                "FROM tb_notes, tb_authors " +
                "WHERE tb_authors.unique_name = tb_notes.author_unique_name " +
                "AND tb_notes.title ILIKE ? " +
                "ORDER BY tb_notes.created_at DESC";


        List<Note> notes = new ArrayList<>();
        try (PreparedStatement statement = C3p0ConnectionsPool
                .getConnectionFromPool().prepareStatement(selectSql)){
            statement.setString(1, "%" + title + "%");
            try (ResultSet resultSet = statement.executeQuery()){
                // resultSet.setFetchSize(2);
                while (resultSet.next()){
                    Author author = new Author(
                            resultSet.getString("unique_name"),
                            resultSet.getObject("registered_at", LocalDate.class),
                            resultSet.getBoolean("is_active")
                    );
                    Note note = new Note(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("text"),
                            resultSet.getObject("created_at", OffsetDateTime.class),
                            author);
                    notes.add(note);
                }
                return notes;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
// 1 : 1
// 1 : N
// N : M
// [author_unique_name | note_id] | | | |
//    fafe                1
//    sadfa               1
//    fafe                2
// PRIMARY KEY (author_unique_name, note_id)