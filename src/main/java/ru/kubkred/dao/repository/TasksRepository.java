package ru.kubkred.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kubkred.dao.entity.TaskInfo;

public interface TasksRepository extends JpaRepository<TaskInfo, Integer> {
}
