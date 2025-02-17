package com.example.taskmanager.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<String> tasks = new ArrayList<>();

    public TaskService() {
        tasks.add("Вивчити Spring MVC");
        tasks.add("Реалізувати контролер");
        tasks.add("Створити представлення");
    }


    public List<String> getTasks() {
        return tasks;
    }


    public void addTasks(String taskName) {
        if (taskName != null && !(taskName.trim().isEmpty())) {
            tasks.add(taskName);
        }
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        }
    }
}
