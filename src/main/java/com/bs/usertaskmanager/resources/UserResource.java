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

import com.bs.usertaskmanager.core.User;
import com.bs.usertaskmanager.db.UserDao;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private final UserDao userDao;

	public UserResource(UserDao userDao) {
		this.userDao = userDao;
	}
	
    @POST
    @UnitOfWork
    public User createUser(User user) {
        return userDao.create(user);
    }

    @GET
    @UnitOfWork
    public List<User> listUsers() {
        return userDao.findAll();
    }
    
    @GET
    @Path("/{user_id}")
    @UnitOfWork
    public User getUser(@PathParam("user_id") LongParam userId) {
        return find(userId.get());
    }
    
    @DELETE
    @Path("/{user_id}")
    @UnitOfWork
    public User deleteUser(@PathParam("user_id") LongParam userId) {
        return delete(userId.get());
    }
    
    @PUT
    @Path("/{user_id}")
    @UnitOfWork
    public User updateUser(@PathParam("user_id") LongParam userId, User user) {
        return update(userId.get(), user);
    }

//    @GET
//    @Path("/view_freemarker")
//    @UnitOfWork
//    @Produces(MediaType.TEXT_HTML)
//    public PersonView getPersonViewFreemarker(@PathParam("personId") LongParam personId) {
//        return new PersonView(PersonView.Template.FREEMARKER, findSafely(personId.get()));
//    }
    
//	@GET
//	public User getUser(@PathParam("user_id") LongParam userId) {
//		return find(userId.get());
//	}
//	
//	@PUT
//    @UnitOfWork
//	public User updateUser(@PathParam("user_id") LongParam userId) {
//		return update(userId.get());
//	}

	private User find(long userId)
	{
		return userDao.findById(
				userId).orElseThrow(
						()-> new NotFoundException("No such user exists."));
	}

	private User update(long userId, User updatedUser)
	{
		return userDao.updateById(
				userId, updatedUser).orElseThrow(
						()-> new NotFoundException("No such user exists."));
	}

	private User delete(long userId)
	{
		return userDao.deleteById(
				userId).orElseThrow(
						()-> new NotFoundException("No such user exists."));
	}
}
