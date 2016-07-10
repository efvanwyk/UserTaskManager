package com.bs.usertaskmanager.resources;

import java.util.List;

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

@Path("/user/{user_id}/task")
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
        return taskDao.findAll(user_id.get());
    }
    
//    @GET
//    @Path("/{task_id}")
//    @UnitOfWork
//    public Task getTask(@PathParam("task_id") LongParam task_id) {
//        return find(task_id.get());
//    }
    
//    @DELETE
//    @Path("/{task_id}")
//    @UnitOfWork
//    public Task deleteTask(@PathParam("task_id") LongParam userId) {
//        return delete(userId.get());
//    }
    
//    @PUT
//    @Path("/{task_id}")
//    @UnitOfWork
//    public Task updateTask(@PathParam("task_id") LongParam userId, Task user) {
//        return update(userId.get(), user);
//    }

//    @GET
//    @Path("/view_freemarker")
//    @UnitOfWork
//    @Produces(MediaType.TEXT_HTML)
//    public PersonView getPersonViewFreemarker(@PathParam("personId") LongParam personId) {
//        return new PersonView(PersonView.Template.FREEMARKER, findSafely(personId.get()));
//    }
    
//	@GET
//	public Task getTask(@PathParam("userId") LongParam userId) {
//		return find(userId.get());
//	}
//	
//	@PUT
//    @UnitOfWork
//	public Task updateTask(@PathParam("userId") LongParam userId) {
//		return update(userId.get());
//	}

	private Task find(long id)
	{
		return taskDao.findById(
				id).orElseThrow(
						()-> new NotFoundException("No such user exists."));
	}

	private Task update(long id, Task updatedTask)
	{
		return taskDao.updateById(
				id, updatedTask).orElseThrow(
						()-> new NotFoundException("No such user exists."));
	}
}
