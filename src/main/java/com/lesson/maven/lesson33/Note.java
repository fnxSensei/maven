package com.lesson.maven.lesson33;

import java.time.*;

public class Note {
    private long id;
    private String title;
    private String text;
    private OffsetDateTime createdAt;
    private Author author;

    public Note(long id, String title, String text, OffsetDateTime createdAt, Author author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getCreatedAtWithZone() {
        return createdAt.atZoneSameInstant(ZoneId.systemDefault());
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }
}