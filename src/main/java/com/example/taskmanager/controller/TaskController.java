package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addTask(@RequestParam("taskName") String taskName) {
        taskService.addTask(taskName);
        return "redirect:/tasks";
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }
}