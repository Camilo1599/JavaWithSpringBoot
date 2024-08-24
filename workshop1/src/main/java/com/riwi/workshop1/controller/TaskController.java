package com.riwi.workshop1.controller;


import com.riwi.workshop1.model.Task;
import com.riwi.workshop1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String TaskList(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task/list";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "taskForm";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model){
        Task task = taskService.getTaskById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Task Id:" + id));
        model.addAttribute("task",task);
        return "task/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id,@ModelAttribute Task task){
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }



}
