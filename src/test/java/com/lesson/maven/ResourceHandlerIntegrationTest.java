package com.lesson.maven;

import org.junit.jupiter.api.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Resource Handler IT")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // порядок выполнения - по номерам @Order
public class ResourceHandlerIntegrationTest {

    @Test
    @Order(1)
    void addSuccessWithValidName() {
        ResourceHandler resourceHandler = new ResourceHandler(new ResourceStorage());
        int id = 1;
        String name = "new resource";
        Resource created = resourceHandler.add(id, name);
        assertAll("success add",
                () -> assertEquals(created.name(), name.toUpperCase(), "Not equals by name"),
                () -> assertInstanceOf(Integer.class, created.id(), "id exception")
        );
    }

    @Test
    @Order(2)
    void addFailedWithInvalidName() {
        ResourceHandler resourceHandler = new ResourceHandler(new ResourceStorage());
        int id = 1;
        String name = null;
        assertThrows(IllegalArgumentException.class, () -> {
            resourceHandler.add(id, name);
        });
    }

}