package com.bs.usertaskmanager.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class XUsernameInUseException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public XUsernameInUseException(String username) 
	{
		super("The username '"+username+"' is already in use.");
	}
}