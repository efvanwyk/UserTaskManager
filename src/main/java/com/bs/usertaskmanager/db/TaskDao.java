package com.bs.usertaskmanager.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.bs.usertaskmanager.core.Task;

public class TaskDao extends BaseHibernateDao<Task> {

	public TaskDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Task create(Task task) {
		return persist(task);
	}

	@Override
	public Optional<Task> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Task> updateById(Long id, Task update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Task> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Task> findAll(Long user_id) {
		return list(namedQuery(Task.QUERY_FIND_ALL_BY_USER_ID)
				.setParameter("user_id", String.valueOf(user_id)));
	} 
	
//	public List<Employee> findByName(String name) {
//        StringBuilder builder = new StringBuilder("%");
//        builder.append(name).append("%");
//        return list(
//                namedQuery("com.javaeeeee.dwstart.core.Employee.findByName")
//                .setParameter("name", builder.toString())
//        );
//    }
}
