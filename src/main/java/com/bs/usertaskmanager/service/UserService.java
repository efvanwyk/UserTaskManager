package com.bs.usertaskmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.usertaskmanager.model.User;
import com.bs.usertaskmanager.model.XUserNotFoundException;
import com.bs.usertaskmanager.model.XUsernameInUseException;
import com.bs.usertaskmanager.repo.UserRepository;

@Service
class UserService 
{
//	UserRepository userRepo = new UserRepositoryFactory().getUserRepository();
	@Autowired
	private UserRepository userRepo;
	
	public List<User> getUsers()
	{
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(u -> users.add(u));
		return users;
	}
	
	public User getUser(long id)
	{
		return userRepo.findById(id).orElseThrow(() -> new XUserNotFoundException(id));
	}
	
	private void validateUsername(User toValidate)
	{
		String username = toValidate.getUsername();
		if (userRepo.findByUsername(username).isPresent())
			throw new XUsernameInUseException(username);
	}
	
	public User createUser(User user)
	{
		validateUsername(user);
		return userRepo.save(user);
	}
	
	public void deleteUser(long id)
	{
		userRepo.deleteById(id);
	}
	
	public User updateUser(long id, User update)
	{
		// even though userRepo's save performs an upsert of the provided user, first load existing
		// user for user validation and data integrity.
		User user = getUser(id);
		user.update(update);
		userRepo.save(user);
		return user;
	}
}
