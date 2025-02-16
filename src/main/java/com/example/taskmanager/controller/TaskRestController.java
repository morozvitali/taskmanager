package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    @GetMapping("/tasks")
    public List<String> getTasks() {
        return Arrays.asList("Вивчити Spring MVC", "Реалізувати контролер", "Створити представлення");
    }
}