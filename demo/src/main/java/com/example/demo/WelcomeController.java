package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<Task> taskList = new ArrayList<Task>();

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());

        return "welcome"; //view
    }

    @PostMapping("/")
    public String welcomeSubmit(Model model, @ModelAttribute Task task) {
        int size = taskList.size();
        size = size + 1;
        task.setId(size);
        taskList.add(task);
        

        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());
        return "welcome";
    }

    @PostMapping("/clear")
    public String clearTasks(Model model) {
        System.out.println("clearing tasks");
        taskList.clear();
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());
        return "welcome";
    }

}