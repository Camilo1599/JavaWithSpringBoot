package com.riwi.workshop1.repository;

import com.riwi.workshop1.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
