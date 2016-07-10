package com.bs.usertaskmanager;

import com.bs.usertaskmanager.core.User;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserTest extends TestCase {
	
	public UserTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(UserTest.class);
	}
	
	public void testUpdateFromUser()
	{
		User user = new User("username", "first_name", "last_name");
		User update = new User("username_new", "first_name_new", "last_name_new");
		
		user.updateFromUser(update);
		assertEquals(update.getUsername(), user.getUsername());
		assertEquals(update.getFirst_name(), user.getFirst_name());
		assertEquals(update.getLast_name(), user.getLast_name());
	}
	
	public void testUpdateFromUser_NullUsername()
	{
		User user = new User("username", "first_name", "last_name");
		User update = new User();
		update.setUsername(null);
		update.setFirst_name("first_name_new");
		update.setLast_name("last_name_new");
		
		user.updateFromUser(update);
		assertEquals("username", user.getUsername());
		assertEquals(update.getFirst_name(), user.getFirst_name());
		assertEquals(update.getLast_name(), user.getLast_name());
	}
	
	public void testUpdateFromUser_NullFirstname()
	{
		User user = new User("username", "first_name", "last_name");
		User update = new User();
		update.setUsername("username_new");
		update.setFirst_name(null);
		update.setLast_name("last_name_new");
		
		user.updateFromUser(update);
		assertEquals(update.getUsername(), user.getUsername());
		assertEquals("first_name", user.getFirst_name());
		assertEquals(update.getLast_name(), user.getLast_name());
	}
	
	public void testUpdateFromUser_NullLastname()
	{
		User user = new User("username", "first_name", "last_name");
		User update = new User();
		update.setUsername("username_new");
		update.setFirst_name("first_name_new");
		update.setLast_name(null);
		
		user.updateFromUser(update);
		assertEquals(update.getUsername(), user.getUsername());
		assertEquals(update.getFirst_name(), user.getFirst_name());
		assertEquals("last_name", user.getLast_name());
	}
}
