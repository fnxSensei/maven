package com.lesson.maven;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Resource Handler UT")
@TestMethodOrder(MethodOrderer.DisplayName.class) // порядок выполнения - по алфавиту @DisplayName
public class ResourceHandlerUnitTest {

    ResourceHandler resourceHandler;

    @Mock
    ResourceStorage resourceStorage;

    @BeforeEach
    void setUp() {
        resourceHandler = new ResourceHandler(resourceStorage);
    }

    @Test
    void addSuccessWithValidNameAndId(){
        Resource expected = new Resource(1, "resource 01".toUpperCase());
        Mockito.when(resourceStorage.save(Mockito.any(Resource.class))).thenReturn(expected);
        Resource created = resourceHandler.add(1, "resource 01");
        assertEquals(expected.name(), created.name());
    }
}