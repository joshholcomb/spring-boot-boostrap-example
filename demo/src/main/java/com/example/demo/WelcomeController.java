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

    private List<String> tasks = Arrays.asList("task1", "task2", "task3");
    private List<Task> taskList = new ArrayList<Task>();

    @GetMapping("/welcome")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());

        return "welcome"; //view
    }

    @PostMapping("/welcome")
    public String welcomeSubmit(Model model, @ModelAttribute Task task) {
        taskList.add(task);
        int listSize = taskList.size();
        System.out.println("task list size: " + listSize);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", new Task());
        return "welcome";
    }

}