package com.bs.usertaskmanager.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bs.usertaskmanager.model.Task;
import com.bs.usertaskmanager.model.Task.TaskStatus;
import com.bs.usertaskmanager.repo.TaskRepository;

@Component
public class TaskCompletionScheduledTask 
{
	private static final String SOURCE = "Task Completion Scheduler";
	private static final int TASK_RATE_MS = 20000; // 20 seconds
	
    private static final Logger logger = LoggerFactory.getLogger(TaskCompletionScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    TaskRepository taskRepo;
    
    @Scheduled(fixedRate = TASK_RATE_MS)
    public void scheduleTaskWithFixedRate() 
    {
    	completeFinishedTasks();
    }
    
    private void completeFinishedTasks()
    {
    	List<Task> tasks = new ArrayList<>();
        taskRepo.findAll().forEach(tasks::add);
        for(Task t : tasks)
        	completeTaskIfRequired(t);
    }
    
    private void completeTaskIfRequired(Task task)
    {
    	Date now = new Date();
    	if (TaskStatus.PENDING.getDisplayName().equals(task.getStatus()) && task.getDatetime().before(now))
		{
    		task.markAsDone();
    		taskRepo.save(task);
    		log("Task '"+task.getId() + ":"+task.getName()+"' with date '"+task.getDatetime()+"' marked as "+TaskStatus.DONE.getDisplayName() + ".");
    	}
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
