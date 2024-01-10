package com.lesson.maven;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

// Mokito
public class ValidatorsTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
    }

    @Test
    public void testName() {
        // import static org.junit.jupiter.api.Assertions.*;
        // assertTrue(Validators.isPositive(20));
        assertAll("positiveTrue",
                () -> assertTrue(Validators.isPositive(20)),
                () -> assertFalse(Validators.isPositive(-10)),
                () -> assertTrue(Validators.isPositive(1000))
        );
    }
}