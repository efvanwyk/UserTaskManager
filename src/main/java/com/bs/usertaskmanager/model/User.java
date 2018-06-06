package com.bs.usertaskmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.bs.util.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NaturalId
	private String username;
	@Column(name = "first_name")
	private String firstname;
	@Column(name = "last_name")
	private String lastname;
	
	public User()
	{
	}
	
	public User(String username, String firstname, String lastname) 
	{
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	// Prevent external callers from creating an id-only user.
	private User(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	/**
	 * @return the firstname
	 */
	@JsonProperty("first_name")
	public String getFirstname() 
	{
		return firstname;
	}
	
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}
	
	/**
	 * @return the lastname
	 */
	@JsonProperty("last_name")
	public String getLastname() 
	{
		return lastname;
	}
	
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	
	public User update(User update)
	{
		if (!ValidationUtil.checkIsNullOrEmpty(update.getFirstname()))
			this.firstname = update.getFirstname();
		if (!ValidationUtil.checkIsNullOrEmpty(update.getLastname()))
			this.lastname = update.getLastname();
		return this;
	}
	
	public static User getUserAssociation(long userId)
	{
		return new User(userId);
	}
}
