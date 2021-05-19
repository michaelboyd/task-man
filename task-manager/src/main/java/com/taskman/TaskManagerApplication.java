package com.taskman;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taskman.entity.Hero;
import com.taskman.entity.Task;
import com.taskman.entity.TaskStatus;
import com.taskman.entity.User;
import com.taskman.service.HeroService;
import com.taskman.service.TaskService;
import com.taskman.service.UserService;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService, TaskService taskService, HeroService heroService) {
		return args -> {
			
			Stream.of("Dick", "Tom", "Harry", "Jane", "Sally", "Susan").forEach(name -> {
				User user = new User(name, name.toLowerCase() + "@email.com");
				userService.create(user);
			});
			userService.getAllUsers().forEach(System.out::println);
			
			Stream.of("Michael", "David", "Alice", "Leslie", "Jesslie", "Rachael").forEach(name -> {
				Hero hero = new Hero(name);
				heroService.create(hero);
			});
			heroService.getHeroes().forEach(System.out::println);			
			
			Stream.of(
					"Create Frontend"
					).forEach(name -> {
				Task task = new Task(name, TaskStatus.NEW.name());
				taskService.create(task);
			});
			Stream.of(
					"Configure Database", 
					"Create Entities and Repositotires",
					"Create Services",
					"Create Controllers"
					).forEach(name -> {
				Task task = new Task(name, TaskStatus.IN_PROGRESS.name());
				taskService.create(task);
			});
			Stream.of(
					"Create Spring Boot Project", 
					"Add any non-starter dependencies", 
					"Create Custom Exception Handler", 
					"Create Tests"
					).forEach(name -> {
				Task task = new Task(name, TaskStatus.COMPLETE.name());
				taskService.create(task);
			});			
			
			
			taskService.getAllTasks().forEach(System.out::println);
			
		};
	}

}
