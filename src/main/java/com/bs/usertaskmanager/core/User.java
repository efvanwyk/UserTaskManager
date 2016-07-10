package com.bs.usertaskmanager.core;

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
@Table(name = "user")
@NamedQueries({
        @NamedQuery(
                name = User.QUERY_FIND_ALL,
                query = "SELECT u FROM User u"
        )
//        ,
//        @NamedQuery(
//        		name = "com.javaeeeee.dwstart.core.Employee.findByName",
//        query = "select e from Employee e "
//        + "where e.firstName like :name "
//        + "or e.lastName like :name")
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "last_name", nullable = false)
	private String last_name;

	public User() {
	}

	public User(String username, String first_name, String last_name) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	@JsonProperty
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@JsonProperty
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("user_id", user_id)
				.add("username", username)
				.add("first_name", first_name)
				.add("last_name", last_name)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		final User that = (User) o;

		return Objects.equals(this.user_id, that.user_id) && 
				Objects.equals(this.username, that.username) && 
				Objects.equals(this.first_name, that.first_name) && 
				Objects.equals(this.last_name, that.last_name);
	}
	
	public void updateFromUser(User update)
	{
		if(update == null)
		{
			return;
		}
		
		if(update.getUsername() != null){
			setUsername(update.getUsername());
		}
		if(update.getFirst_name() != null){
			setFirst_name(update.getFirst_name());
		}
		if(update.getLast_name() != null){
			setLast_name(update.getLast_name());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(user_id, username, first_name, last_name);
	}

	public static final String QUERY_FIND_ALL = "com.bs.usertaskmanager.core.User.findAll";
}
