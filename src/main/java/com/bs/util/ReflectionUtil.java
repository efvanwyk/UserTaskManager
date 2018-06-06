package com.bs.util;

import java.lang.reflect.Field;

public class ReflectionUtil 
{
	public static Object getFieldValue(Object object, String fieldName)
	{
		Object fieldValue = null;
		try
		{
			Field field = getField(fieldName, object);
			field.setAccessible(true);
			fieldValue = field.get(object);
		}
		catch (NoSuchFieldException e)
		{
			throw new RuntimeException("The specified field: '" + fieldName + "' does not exist.");
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return fieldValue;
	}
	
	public static Field getField(String fieldName, Object sourceObject) throws Exception
	{
		Field returnField = null;
		Class<?> theClass = sourceObject.getClass();
		while (theClass.getSuperclass() != null) // one level below Object, no need to do object which everything must inherit from anyway
		{
			Field[] fields = theClass.getDeclaredFields();
			Field fd = null;
			for (int i = 0; i < fields.length; i++)
			{
				fd = fields[i];
				if (fd.getName().equalsIgnoreCase(fieldName))
					returnField = fd;
			}
			theClass = theClass.getSuperclass();
		}
		returnField.setAccessible(true);
		return returnField;
	}
}
