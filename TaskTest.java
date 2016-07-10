package com.bs.usertaskmanager;

import com.bs.usertaskmanager.core.Task;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TaskTest extends TestCase {
	
	public TaskTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(TaskTest.class);
	}
	
	public void testUpdateFromTask()
	{
		Task task = new Task(1L, "name", "description", "2016-01-13 14:25:00");
		Task update = new Task(1L, "name_new", "description_new", "2016-01-25 14:25:00");
		
		task.updateFromTask(update);
		assertEquals(update.getName(), task.getName());
		assertEquals(update.getDescription(), task.getDescription());
		assertEquals(update.getDate_time(), task.getDate_time());
	}
	
	public void testUpdateFromTask_NullName()
	{
		Task task = new Task(1L, "name", "description", "2016-01-13 14:25:00");
		Task update = new Task(1L, null, "description_new", "2016-01-25 14:25:00");
		
		task.updateFromTask(update);
		assertEquals("name", task.getName());
		assertEquals(update.getDescription(), task.getDescription());
		assertEquals(update.getDate_time(), task.getDate_time());
	}
	
	public void testUpdateFromTask_NullDescription()
	{
		Task task = new Task(1L, "name", "description", "2016-01-13 14:25:00");
		Task update = new Task(1L, "name_new", null, "2016-01-25 14:25:00");
		
		task.updateFromTask(update);
		assertEquals(update.getName(), task.getName());
		assertEquals("description", task.getDescription());
		assertEquals(update.getDate_time(), task.getDate_time());
	}
	
	public void testUpdateFromTask_NullDateTime()
	{
		Task task = new Task(1L, "name", "description", "2016-01-13 14:25:00");
		Task update = new Task(1L, "name_new", "description_new", null);
		
		task.updateFromTask(update);
		assertEquals(update.getName(), task.getName());
		assertEquals(update.getDescription(), task.getDescription());
		assertEquals("2016-01-13 14:25:00", task.getDate_time());
	}
}
