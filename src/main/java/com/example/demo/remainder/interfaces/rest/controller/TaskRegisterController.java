package com.example.demo.remainder.interfaces.rest.controller;

import com.example.demo.remainder.application.TaskService;
import com.example.demo.remainder.interfaces.dto.CreateTaskDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@AllArgsConstructor
public class TaskRegisterController {

    private final TaskService taskService;

    @PostMapping("/create")
    private void createTask(@RequestBody CreateTaskDto request){
        taskService.createQuartzTask(request);
    }
}
