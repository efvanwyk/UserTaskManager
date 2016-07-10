package com.bs.usertaskmanager.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bs.usertaskmanager.core.Task;
import com.bs.usertaskmanager.db.TaskDao;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/api/user/{user_id}/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
	
	private final TaskDao taskDao;

	public TaskResource(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
    @POST
    @UnitOfWork
    public Task createTask(@PathParam("user_id") LongParam user_id, Task task) {
        task.setUser_id(user_id.get());
    	return taskDao.create(task);
    }

    @GET
    @UnitOfWork
    public List<Task> listTasks(@PathParam("user_id") LongParam user_id) {
        return taskDao.findAllByUserId(user_id.get());
    }

    @GET
    @Path("/{task_id}")
    @UnitOfWork
    public Task getTaskByUserId(@PathParam("user_id") LongParam user_id, @PathParam("task_id") LongParam task_id) {
        return findSafelyByUserId(task_id.get(), user_id.get());
    }
    
    @DELETE
    @Path("/{task_id}")
    @UnitOfWork
    public Task deleteTask(@PathParam("user_id") LongParam user_id, @PathParam("task_id") LongParam task_id) {
        return deleteSafelyByUserId(task_id.get(), user_id.get());
    }
    
    @PUT
    @Path("/{task_id}")
    @UnitOfWork
    public Task updateTaskByUserId(@PathParam("user_id") LongParam user_id, @PathParam("task_id") LongParam task_id, Task task) {
        return updateSafelyByUserId(task_id.get(), user_id.get(), task);
    }

	private Task findSafelyByUserId(long task_id, long user_id)
	{
		return taskDao.findByUserId(
				task_id, user_id).orElseThrow(
						()-> new NotFoundException("No such task exists for user."));
	}

	private Task updateSafelyByUserId(long task_id, long user_id, Task updated_task)
	{
		return taskDao.updateByUserId(
				task_id, user_id, updated_task).orElseThrow(
						()-> new NotFoundException("No such task exists for user."));
	}

	private Task deleteSafelyByUserId(long task_id, long user_id)
	{
		return taskDao.deleteByUserId(
				task_id, user_id).orElseThrow(
						()-> new NotFoundException("No such task exists for user."));
	}
}
