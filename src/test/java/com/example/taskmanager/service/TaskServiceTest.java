package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        // Створюємо мок об'єкт репозиторію
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void testGetTasks() {
        // Підготовка тестових даних
        List<Task> mockTasks = Arrays.asList(
                new Task("Завдання 1"),
                new Task("Завдання 2")
        );

        // Налаштовуємо мок-об'єкт
        when(taskRepository.findAll()).thenReturn(mockTasks);

        // Виклик методу
        List<Task> tasks = taskService.getTasks();

        // Перевірка
        assertEquals(2, tasks.size());
        assertEquals("Завдання 1", tasks.get(0).getName());
    }

    @Test
    void testAddTask() {
        Task task = new Task("Нове завдання");
        taskService.addTask(task.getName());

        // Перевіряємо, що `save()` викликався один раз
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("Видалити це завдання");
        task.setId(1L);

        // Симулюємо поведінку `findById()`
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Видаляємо завдання
        taskService.deleteTask(1L);

        // Перевіряємо, що `deleteById()` викликаний один раз
        verify(taskRepository, times(1)).deleteById(1L);
    }
}