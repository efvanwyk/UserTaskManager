package com.bs.usertaskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bs.usertaskmanager.model.User;

@RestController
public class UserRestController 
{	
	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.GET, value="/api/user")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}

	@RequestMapping(method=RequestMethod.GET, value="/api/user/{id}")
	public User getUser(@PathVariable long id)
	{
		return userService.getUser(id);
	}

	@RequestMapping(method=RequestMethod.POST, value="/api/user")
	public void addUser(@RequestBody User user)
	{
		userService.createUser(user);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/api/user/{id}")
	public void deleteUser(@PathVariable long id)
	{
		userService.deleteUser(id);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/api/user/{id}")
	public void updateUser(@PathVariable long id, @RequestBody User user)
	{
		userService.updateUser(id, user);
	}
}
