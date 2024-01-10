package com.lesson.maven;

public class ResourceHandler {
    private final ResourceStorage resourceStorage;

    public ResourceHandler(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public Resource add(int id, String name){
        if (name == null) throw new IllegalArgumentException("name is null");
        if (id <= 0) throw new IllegalArgumentException("id isn't positive");
        return resourceStorage.save(new Resource(id, name.toUpperCase()));
    }
}