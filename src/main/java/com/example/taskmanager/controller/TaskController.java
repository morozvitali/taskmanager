package com.example.taskmanager.controller;


import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private List <String> tasks = new ArrayList<>();


    public TaskController() {
        tasks.add("Вивчити Spring MVC");
        tasks.add("Реалізувати контролер");
        tasks.add("Створити представлення");
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "tasks";
    }


    @PostMapping("/tasks")
    public String addTasks(@RequestParam("taskName") String taskName) {
        if (taskName != null && !taskName.trim().isEmpty()) {
            tasks.add(taskName);
        }

        return "redirect:/tasks"; // перенаправлення назад до списку
    }
}
