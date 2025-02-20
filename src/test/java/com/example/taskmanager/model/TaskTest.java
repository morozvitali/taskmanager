package com.example.taskmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task("Перевірити модель");

        assertEquals("Перевірити модель", task.getName());
        assertNull(task.getId()); // ID має бути null до збереження у базі
    }

    @Test
    void testSettersAndGetters() {
        Task task = new Task();
        task.setName("Змінене завдання");

        assertEquals("Змінене завдання", task.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Task task1 = new Task("Однакове завдання");
        Task task2 = new Task("Однакове завдання");

        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}