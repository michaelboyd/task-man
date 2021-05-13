package com.taskman.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskman.entity.Task;
import com.taskman.entity.TaskStatus;
import com.taskman.repository.TaskRepository;
import com.taskman.service.TaskService;
import com.taskman.service.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	
	@Mock
	private TaskRepository taskRepository;
	
	private TaskService underTest;
	
	@BeforeEach
	void setUp() {
		underTest = new TaskServiceImpl(taskRepository);
	}	
	
	@Test
	void testGetAllTasks() {
		//when
		underTest.getAllTasks();
		//then
		verify(taskRepository).findAll();
	}
	
	@Test
	void testGetTaskByTaskId() {
		//given
		Task task = new Task("Test Something", TaskStatus.NEW.name());
        doReturn(Optional.of(task)).when(taskRepository).findById(Mockito.anyLong());
        //when
        Optional<Task> returnedTask = underTest.getTask(Mockito.anyLong());
		//then
        assertThat(returnedTask.isPresent()).isTrue();
        assertThat(returnedTask.get()).isSameAs(task);
		
	}
	
	@Test
	void testGetTasksByTaskStatus() {
		//given
		List <Task> tasks = new ArrayList<>();
		Task task = new Task("Test Something", TaskStatus.NEW.name());
        tasks.add(task);
		doReturn((tasks)).when(taskRepository).findByStatus(TaskStatus.NEW.name());
        //when
        Iterable <Task> returnedTasks = underTest.getTasksByStatus(TaskStatus.NEW.name());
		//then
        assertThat(returnedTasks).isNotEmpty();
	}
	
	@Test
	void testUpdateTask() {
		Task task = new Task("Test Something", TaskStatus.NEW.name());
		underTest.update(task);
		verify(taskRepository).save(task);
	}
	
	@Test
	void testCreateTask() {
		//given
		Task task = new Task("Test Something", TaskStatus.NEW.name());
		//when
		underTest.create(task);
		//then
		ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
		verify(taskRepository).save(taskArgumentCaptor.capture());
		Task capturedTask = taskArgumentCaptor.getValue();
		assertThat(capturedTask).isSameAs(task);
		assertThat(capturedTask).isEqualTo(task);
	}	
	

}
