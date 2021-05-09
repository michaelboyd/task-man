package com.taskman.repository;

import org.springframework.data.repository.CrudRepository;

import com.taskman.entity.UserTask;
import com.taskman.entity.UserTaskPK;

public interface UserTaskRepository extends CrudRepository<UserTask, UserTaskPK> {

}
