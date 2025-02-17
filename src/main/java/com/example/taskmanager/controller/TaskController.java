package com.example.taskmanager.controller;


import com.example.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String addTasks(@RequestParam("taskName") String taskName) {
        taskService.addTasks(taskName);
        return "redirect:/tasks";
    }

@PostMapping("/delete-task")
public String deleteTask(@RequestParam("taskIndex") int taskIndex) {
taskService.deleteTask(taskIndex);
return "redirect:/tasks";
    }

}