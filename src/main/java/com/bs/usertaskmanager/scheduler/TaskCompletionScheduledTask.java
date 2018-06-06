package com.bs.usertaskmanager.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bs.usertaskmanager.model.Task;
import com.bs.usertaskmanager.model.Task.TaskStatus;
import com.bs.usertaskmanager.service.TaskService;

@Component
public class TaskCompletionScheduledTask 
{
	private static final String SOURCE = "Task Completion Scheduler";
	private static final int TASK_RATE_MS = 20000; // 20 seconds
	
    private static final Logger logger = LoggerFactory.getLogger(TaskCompletionScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    TaskService taskService;
    
    @Scheduled(fixedRate = TASK_RATE_MS)
    public void scheduleTaskWithFixedRate() 
    {
    	processOldPendingTasks();
    }
    
    private void processOldPendingTasks()
    {
    	List<Task> tasks = taskService.getTasksByStatusAndBeforeDate(TaskStatus.PENDING, new Date());
    	tasks.forEach(t -> completeTask(t));
    }
    
    private void completeTask(Task task)
    {
    	task.markAsDone();
		taskService.updateTask(task);
		log("Task '"+task.getId() + ":"+task.getName()+"' for date '"+task.getDatetime()+"' update to "+TaskStatus.DONE.getDisplayName() + ".");
    }
    
    private void log(String log)
	{
		logger.info(new StringBuilder(SOURCE).append(" :: ").append(dateTimeFormatter.format(LocalDateTime.now()))
				.append(" :: ").append(log).toString());
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
