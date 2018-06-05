package com.bs.usertaskmanager.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bs.usertaskmanager.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> 
{
	public Iterable<Task> findByName(String name);
	public Iterable<Task> findByDatetime(String datetime);
	public Iterable<Task> findByUserId(long userId);
	public Optional<Task> findByIdAndUserId(long id, long userId);
}