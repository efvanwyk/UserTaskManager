package com.bs.usertaskmanager.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class XTaskNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public XTaskNotFoundException(long id, long userId) 
	{
		super(new StringBuilder("Could not find task for userId '").append(userId).append("' and taskId '")
				.append(id).append("'").toString());
	}
}