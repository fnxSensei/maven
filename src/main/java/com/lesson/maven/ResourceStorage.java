package com.lesson.maven;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    private final Map<Integer, Resource> resources = new HashMap<>();

    public Resource save(Resource resource) {
        if (resource == null) throw new IllegalArgumentException("resource is null");
        resources.put(resource.id(), resource);
        return resource;
    }

}