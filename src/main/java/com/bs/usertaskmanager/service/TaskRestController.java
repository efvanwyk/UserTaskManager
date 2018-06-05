package com.bs.usertaskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bs.usertaskmanager.model.Task;

@RestController
public class TaskRestController 
{	
	@Autowired
	private TaskService taskService;

	@RequestMapping(method=RequestMethod.GET, value="/api/user/{userId}/task")
	public List<Task> getTasksForUser(@PathVariable long userId)
	{
		return taskService.getTasks(userId);
	}

	@RequestMapping(method=RequestMethod.GET, value="/api/user/{userId}/task/{id}")
	public Task getTask(@PathVariable long id, @PathVariable long userId)
	{
		return taskService.getTask(id, userId);
	}

	@RequestMapping(method=RequestMethod.POST, value="/api/user/{userId}/task")
	public void addTask(@RequestBody Task task, @PathVariable long userId)
	{
		task.setUserFromId(userId);
		taskService.createTask(task, userId);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/api/user/{userId}/task/{id}")
	public void deleteTask(@PathVariable long id, @PathVariable long userId)
	{
		taskService.deleteTask(id, userId);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/api/user/{userId}/task/{id}")
	public void updateTask(@PathVariable long id, @RequestBody Task task, @PathVariable long userId)
	{
		task.setUserFromId(userId);
		taskService.updateTask(id, task, userId);
	}
}
