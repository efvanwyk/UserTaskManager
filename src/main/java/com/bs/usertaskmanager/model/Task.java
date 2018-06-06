package com.bs.usertaskmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.bs.util.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "task")
public class Task 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@Column(name = "date_time", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date datetime;
	private String status;
	
//	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
//	private Long userId;
	@ManyToOne()
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User user;
	
	public Task()
	{
	}
	
	@PrePersist
	public void prePersist()
	{
		if (ValidationUtil.checkIsNullOrEmpty(this.status))
			this.status = TaskStatus.PENDING.getDisplayName();
	}
	
	public Task(String name, String description, Date datetime) 
	{
		this(name, description, datetime, TaskStatus.PENDING.getDisplayName());
	}

	public Task(String name, String description, Date datetime, String status) 
	{
		this.name = name;
		this.description = description;
		this.datetime = datetime;
	}

	/**
	 * @return the id
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}

	/**
	 * @return the datetime
	 */
	@JsonProperty("date_time")
	public Date getDatetime() 
	{
		return datetime;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date datetime) 
	{
		this.datetime = datetime;
	}

	/**
	 * @return the userId
	 */
	public User getUser() 
	{
		return user;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserFromId(long userId)
	{
		this.user = User.getUserAssociation(userId);
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUser(User user) 
	{
		this.user = user;
	}

	/**
	 * @return the status
	 */
	public String getStatus() 
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public Task update(Task update)
	{
		if (!ValidationUtil.checkIsNullOrEmpty(update.getName()))
			this.name = update.getName();
		if (!ValidationUtil.checkIsNullOrEmpty(update.getDescription()))
			this.description = update.getDescription();
		if (update.getDatetime() != null)
			this.datetime = update.getDatetime();
		if (!ValidationUtil.checkIsNullOrEmpty(update.getStatus()))
			this.status = update.getStatus();
		return this;
	}
	
	public String toString()
	{ 
		return "Task{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", datetime='" + datetime + '\'' +
            '}';
	}
	
	public void markAsDone()
	{
		this.status = TaskStatus.DONE.getDisplayName();
	}
	
	public enum TaskStatus
	{
		NONE(""),
		PENDING("pending"),
		DONE("done");
		
		String displayName;
		
		TaskStatus(String displayName)
		{
			this.displayName = displayName;
		}
		
		public String getDisplayName() 
		{
			return displayName;
		}

		public static TaskStatus getByDisplayName(String displayName) 
		{
		    for(TaskStatus status : values()) 
		    {
		        if(status.displayName.equals(displayName)) 
		        	return status;
		    }
		    return NONE;
		}
	}
}
