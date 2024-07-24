package com.zap.controller;


import com.zap.model.Task;
import com.zap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<Task>> listTask() {
        var res = taskService.findAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public Task byId(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        var taskCreated = taskService.save(task);
        return ResponseEntity.ok(taskCreated);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        var taskNew = taskService.updata(id, task);
        return ResponseEntity.ok(taskNew);
    }
}
