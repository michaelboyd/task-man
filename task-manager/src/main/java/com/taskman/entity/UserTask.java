package com.taskman.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserTask {
	
    @EmbeddedId
    @JsonIgnore
    private UserTaskPK pk;

    @Column(nullable = false) 
    private String status;

	public UserTask() {
		super();
	}

	public UserTask(User user, Task task, String status) {
		this.pk = new UserTaskPK();
		this.pk.setUser(user);
		this.pk.setTask(task);
		this.status = status;
	}
	
	@Transient
	public Task getTask() {
		return this.pk.getTask();
	}

	public UserTaskPK getPk() {
		return pk;
	}

	public void setPk(UserTaskPK pk) {
		this.pk = pk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserTask)) {
			return false;
		}
		UserTask other = (UserTask) obj;
		if (pk == null) {
			if (other.pk != null) {
				return false;
			}
		} else if (!pk.equals(other.pk)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
}
