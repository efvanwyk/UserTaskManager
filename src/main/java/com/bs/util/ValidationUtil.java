package com.bs.util;

public class ValidationUtil 
{
	public static boolean checkIsNullOrEmpty(String value)
	{
		return value == null || value.length() == 0;
	}
	
	public static void isSet(String name, Object value)
	{
		if ((value instanceof String && checkIsNullOrEmpty((String) value)) || value == null)
			throw new RuntimeException("Value for " + name + " must be provided.");
	}
}
