package com.bs.usertaskmanager.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.bs.usertaskmanager.core.Task;

public class TaskDao extends BaseHibernateDao<Task> {
	public TaskDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Task create(Task task) {
		return persist(task);
	}

	public Optional<Task> findById(Long id) {
		return Optional.ofNullable(get(id));
	}

	public Optional<Task> deleteById(Long id) {
		return Optional.ofNullable(get(id));
	}

	public Optional<Task> findByUserId(Long id, Long user_id) {
		Optional<Task> task = Optional.ofNullable(get(id));
		if(task.isPresent() && task.get().getUser_id() == user_id)
		{
			return task;
		}
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<Task> updateById(Long id, Task update) {
		Task task = null;
		Optional<Task> found = findById(id);
		if(found.isPresent())
		{
			task = found.get();
			task.updateFromTask(update);
			currentSession().update(task);
		}
		return Optional.ofNullable(task);
	}
	
	public Optional<Task> updateByUserId(Long task_id, Long user_id, Task update) {
		Task task = null;
		Optional<Task> found = findByUserId(task_id, user_id);
		if(found.isPresent())
		{
			task = found.get();
			task.updateFromTask(update);
			currentSession().update(task);
		}
		return Optional.ofNullable(task);
	}

	public Optional<Task> deleteByUserId(Long id, Long user_id) {
		Optional<Task> task = findByUserId(id, user_id);
		if(task.isPresent())
		{
			currentSession().delete(task.get());
		}
		return task;
	}
	
	public List<Task> findAll() {
		return list(namedQuery(Task.QUERY_FIND_ALL));
	}
	
	public List<Task> findAllByUserId(Long user_id) {
		return list(namedQuery(Task.QUERY_FIND_ALL_BY_USER_ID)
				.setParameter("user_id", user_id));
	}
}
