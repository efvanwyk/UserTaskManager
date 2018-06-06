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

	@RequestMapping(method=RequestMethod.GET, value="/api/user/{userId}")
	public User getUser(@PathVariable long userId)
	{
		return userService.getUser(userId);
	}

	@RequestMapping(method=RequestMethod.POST, value="/api/user")
	public void addUser(@RequestBody User user)
	{
		userService.createUser(user);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/api/user/{userId}")
	public void deleteUser(@PathVariable long userId)
	{
		userService.deleteUser(userId);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/api/user/{userId}")
	public void updateUser(@PathVariable long userId, @RequestBody User user)
	{
		userService.updateUser(userId, user);
	}
}
