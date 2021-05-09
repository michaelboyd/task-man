package com.taskman.dto;

import com.taskman.entity.Task;

public class UserTaskDTO {
	
	private Task task;
	
	private String status;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
