package com.taskman.repository.it;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.taskman.entity.Task;
import com.taskman.entity.TaskStatus;
import com.taskman.repository.TaskRepository;

@DataJpaTest
public class TaskRepositoryIT {
	
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private TaskRepository taskRepository;	
    
    @Test
    public void whenFindByName_thenReturnTask() {
    	Task task = new Task("New Task", TaskStatus.NEW.name());
        entityManager.persistAndFlush(task);

        List <Task> found = taskRepository.findByName(task.getName());
        assertThat(found).asList();
        assertThat(found).hasSize(1).extracting(Task::getName).containsOnly(task.getName());
    }    

}
