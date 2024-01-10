package com.lesson.maven.lesson33;

import java.time.LocalDate;

public class Author {
    private final String uniqueName;
    private final LocalDate registeredAt;
    private boolean isActive = true;

    public Author(String uniqueName, LocalDate registeredAt, boolean isActive) {
        this.uniqueName = uniqueName;
        this.registeredAt = registeredAt;
        this.isActive = isActive;
    }

    public Author(String uniqueName) {
        this.uniqueName = uniqueName;
        registeredAt = LocalDate.now();
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}