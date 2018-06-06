package com.bs.usertaskmanager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.usertaskmanager.model.Task;
import com.bs.usertaskmanager.model.XTaskNotFoundException;
import com.bs.usertaskmanager.model.Task.TaskStatus;
import com.bs.usertaskmanager.repo.TaskRepository;

@Service
public class TaskService 
{
	@Autowired
	private TaskRepository taskRepo;
	
	public List<Task> getTasks(long userId)
	{
		List<Task> tasks = new ArrayList<>();
		taskRepo.findByUserId(userId).forEach(t -> tasks.add(t));
		return tasks;
	}
	
	public Task getTask(long id, long userId)
	{
		return taskRepo.findByIdAndUserId(id, userId).orElseThrow(() -> new XTaskNotFoundException(id, userId));
	}
	
	public Task createTask(Task task, long userId)
	{
		task.setUserFromId(userId);
		return taskRepo.save(task);
	}
	
	public void deleteTask(long id, long userId)
	{
		getTask(id, userId); // validate that the task exists for the user id
		taskRepo.deleteById(id);
	}
	
	/**
	 * Performs a full update on the provided task.
	 * 
	 * @param task
	 * @return
	 */
	public Task updateTask(Task task)
	{
		return taskRepo.save(task);
	}
	
	public Task updateTask(long id, Task update, long userId)
	{
		// even though taskRepo's save performs an upsert of the provided task, first load existing
		// task to validate presence and control how the update occurs.
		Task task = getTask(id, userId);
		task.update(update);
		taskRepo.save(task);
		return task;
	}
	
	public List<Task> getTasksByStatusAndBeforeDate(TaskStatus taskStatus, Date date)
	{
		List<Task> tasks = new ArrayList<>();
		taskRepo.findByStatusAndDatetimeBefore(taskStatus.getDisplayName(), date).forEach(t -> tasks.add(t));
		return tasks;
	}
}
