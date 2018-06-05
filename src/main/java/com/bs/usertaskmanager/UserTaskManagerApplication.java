package com.bs.usertaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UserTaskManagerApplication {
	
	public static final String APP_NAME = "user-task-manager";
	
	public static void main(String[] args) 
	{
		SpringApplication.run(UserTaskManagerApplication.class, args);
	}
}
