package com.bs.usertaskmanager;

import org.junit.Assert;
import org.junit.Test;

import com.bs.usertaskmanager.model.User;

public class UserTest  
{
	@Test
	public void testUpdateFromUser()
	{
		User user = new User("username", "first_name", "last_name");
		User update = new User("username_new", "first_name_new", "last_name_new");
		user.update(update);

		Assert.assertEquals("username", user.getUsername()); //unchanged
		Assert.assertEquals(update.getFirstname(), user.getFirstname());
		Assert.assertEquals(update.getLastname(), user.getLastname());
	}
}
