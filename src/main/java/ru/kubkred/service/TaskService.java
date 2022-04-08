package ru.kubkred.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.kubkred.dao.entity.TaskInfo;
import ru.kubkred.dao.repository.TasksRepository;

import java.util.List;
import java.util.Objects;

@Component
public class TaskService {

    private TasksRepository repository;
    private TaskInfo info;

    @Autowired
    public void setRepository(TasksRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all tasks from database
     * @return - list with tasks
     */
    public List<TaskInfo> getAll() {
        return repository.findAll();
    }

    /**
     * Returns the task by current id
     * @param id - item id
     * @throws - checks and throws an error in case of a negative or non-existent id value
     * @return - task by id
     */
    public TaskInfo getById(int id) {
        changeId(id);

        return info;
    }

    /**
     * Adds a task to the database
     * @param info - task entity
     * @return - saved task
     */
    public TaskInfo createTask(TaskInfo info) {
        return repository.save(info);
    }

    /**
     * Makes changes for the selected task
     * @param info - task entity
     * @return - modified task
     */
    public TaskInfo updateTask(TaskInfo info) {
        return repository.save(info);
    }

    /**
     * Deletes a task by the given id
     * @throws - checks and throws an error in case of a negative or non-existent id value
     * @param id - item id
     */
    public void deleteById(int id) {
        changeId(id);

        repository.deleteById(id);
    }

    private void changeId(int id) {
        if (id <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        info = repository.findById(id).orElse(null);
        if (Objects.isNull(info)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
