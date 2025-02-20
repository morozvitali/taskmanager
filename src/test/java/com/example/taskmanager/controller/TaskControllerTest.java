package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)  // Тестуємо тільки TaskController
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        // Перед кожним тестом скидаємо виклики мок-об'єкта
        Mockito.reset(taskService);
    }

    @Test
    void testGetTasks() throws Exception {
        // Готуємо тестові дані
        List<Task> mockTasks = Arrays.asList(
                new Task("Завдання 1"),
                new Task("Завдання 2")
        );

        // Симулюємо поведінку taskService
        when(taskService.getTasks()).thenReturn(mockTasks);

        // Виконуємо HTTP-запит GET /tasks
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())  // Перевіряємо, що запит виконався успішно
                .andExpect(view().name("tasks"))  // Очікуємо, що повернеться `tasks.html`
                .andExpect(model().attributeExists("tasks"))  // Перевіряємо, що в моделі є `tasks`
                .andExpect(model().attribute("tasks", hasSize(2))); // Перевіряємо, що список має 2 елементи
    }

    @Test
    void testAddTask() throws Exception {
        // Виконуємо POST-запит для додавання завдання
        mockMvc.perform(post("/tasks")
                        .param("taskName", "Нове завдання"))
                .andExpect(status().is3xxRedirection())  // Очікуємо редірект після успішного додавання
                .andExpect(redirectedUrl("/tasks"));  // Перевіряємо, що після додавання редірект йде на /tasks

        // Перевіряємо, що taskService.addTask() викликався один раз
        verify(taskService, times(1)).addTask("Нове завдання");
    }

    @Test
    void testDeleteTask() throws Exception {
        // Виконуємо POST-запит для видалення завдання з ID = 1
        mockMvc.perform(post("/delete-task")
                        .param("taskId", "1"))
                .andExpect(status().is3xxRedirection())  // Очікуємо редірект після видалення
                .andExpect(redirectedUrl("/tasks"));

        // Перевіряємо, що метод taskService.deleteTask(1L) викликався один раз
        verify(taskService, times(1)).deleteTask(1L);
    }
}