package com.bs.usertaskmanager.core;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "Task")
@NamedQueries({
        @NamedQuery(
                name = Task.QUERY_FIND_ALL_BY_USER_ID,
                query = "SELECT t FROM Task t WHERE t.user_id = :user_id"
        )
//        ,
//        @NamedQuery(name = "com.javaeeeee.dwstart.core.Employee.findByName",
//        query = "select e from Employee e "
//        + "where e.firstName like :name "
//        + "or e.lastName like :name")
//        ,
//        @NamedQuery(
//        		name = "com.javaeeeee.dwstart.core.Employee.findByName",
//        query = "select e from Employee e "
//        + "where e.firstName like :name "
//        + "or e.lastName like :name")
})
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long task_id;

	@Column(name = "user_id", nullable = false)
	private long user_id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "date_time", nullable = true)
	private Date date_time;
	
	public Task() {
	}

	public Task(long user_id, String name, String description, Date date_time) {
		this.user_id = user_id;
		this.name = name;
		this.description = description;
		this.date_time = date_time;
	}
	
	@JsonProperty
	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}
	
	@JsonProperty
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("task_id", task_id)
				.add("user_id", user_id)
				.add("name", name)
				.add("description", description)
				.add("date_time", date_time)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Task)) {
			return false;
		}

		final Task that = (Task) o;

		return Objects.equals(this.task_id, that.task_id) && 
				Objects.equals(this.user_id, that.user_id) && 
				Objects.equals(this.name, that.name) && 
				Objects.equals(this.description, that.description) && 
				Objects.equals(this.date_time, that.date_time);
	}
	
	public void updateFromTask(Task update)
	{
		if(update == null)
		{
			return;
		}
		
		if(update.getName() != null){
			setName(update.getName());
		}
		
		if(update.getDescription() != null){
			setDescription(update.getDescription());
		}
		
		if(update.getDate_time() != null){
			setDate_time(update.getDate_time());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(task_id, user_id, name, description, date_time);
	}

	public static final String QUERY_FIND_ALL_BY_USER_ID = "com.bs.usertaskmanager.core.Task.findAll";
}
