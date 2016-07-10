package com.bs.usertaskmanager.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.bs.usertaskmanager.core.User;

public class UserDao extends BaseHibernateDao<User> {
	public UserDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User create(User user) {
		return persist(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return Optional.ofNullable(get(id));
	}

	@Override
	public Optional<User> updateById(Long id, User update) {
		User user = null;
		Optional<User> found = findById(id);
		if(found.isPresent())
		{
			user = found.get();
			user.updateFromUser(update);
			currentSession().update(user);
		}
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> deleteById(Long id) {
		Optional<User> user = findById(id);
		if(user.isPresent())
		{
			currentSession().delete(user.get());
		}
		return user;
	}

	public List<User> findAll() {
		return list(namedQuery(User.QUERY_FIND_ALL));
	}  
}
