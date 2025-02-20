package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // Автоматично створює тестову базу (H2)
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindById() {
        // Додаємо завдання у базу
        Task task = new Task("Перевірити репозиторій");
        Task savedTask = taskRepository.save(task);

        // Перевіряємо, що ID створено
        assertNotNull(savedTask.getId());

        // Знаходимо завдання у базі
        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        assertTrue(foundTask.isPresent());
        assertEquals("Перевірити репозиторій", foundTask.get().getName());
    }

    @Test
    void testFindAll() {
        // Додаємо два завдання
        taskRepository.save(new Task("Перше завдання"));
        taskRepository.save(new Task("Друге завдання"));

        // Отримуємо всі завдання
        List<Task> tasks = taskRepository.findAll();

        // Має бути 2 елементи
        assertEquals(2, tasks.size());
    }

    @Test
    void testDeleteTask() {
        // Додаємо завдання
        Task task = new Task("Видалити це завдання");
        Task savedTask = taskRepository.save(task);

        // Видаляємо завдання
        taskRepository.deleteById(savedTask.getId());

        // Перевіряємо, що його більше немає у базі
        Optional<Task> deletedTask = taskRepository.findById(savedTask.getId());
        assertFalse(deletedTask.isPresent());
    }
}