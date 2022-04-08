package ru.kubkred.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kubkred.dao.entity.TaskInfo;
import ru.kubkred.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/task", consumes = MediaType.ALL_VALUE)
public class TaskRestController {

    private TaskService service;

    @Autowired
    public void setService(TaskService service) {
        this.service = service;
    }

    /**
     * Getting a list of all tasks
     */
    @GetMapping(value = "/all")
    public List<TaskInfo> getAll() {
        return service.getAll();
    }

    /**
     * Get task by id. Return HttpStatus.BAD_REQUEST or HttpStatus.NOT_FOUND
     * if incorrect id is passed
     */
    @GetMapping
    public TaskInfo getById(@RequestParam("id") int id) {
        return service.getById(id);
    }

    /**
     * Create new task
     */
    @PostMapping
    public TaskInfo createTask(@RequestBody TaskInfo info) {
        return service.createTask(info);
    }

    /**
     * Update the task
     */
    @PutMapping
    public TaskInfo updateTask(@RequestBody TaskInfo info) {
        return service.updateTask(info);
    }

    /**
     * Delete the task by id. Return HttpStatus.BAD_REQUEST or HttpStatus.NOT_FOUND
     */
    @DeleteMapping
    public void deleteTask(@RequestParam("id") int id) {
        service.deleteById(id);
    }
}
