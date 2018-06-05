package com.bs.usertaskmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import com.bs.usertaskmanager.model.Task;

public class TaskTest
{	
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void testUpdateFromTask() throws ParseException
	{
		Task task = new Task("name", "description", dateFormatter.parse("2018-01-12 14:25:00"));
		Task update = new Task("name_new", "description_new", dateFormatter.parse("2018-02-25 14:25:00"));
		
		task.update(update);
		Assert.assertEquals(update.getName(), task.getName());
		Assert.assertEquals(update.getDescription(), task.getDescription());
		Assert.assertEquals(update.getDatetime(), task.getDatetime());
	}
}
