package com.bs.usertaskmanager.db;

import java.util.Optional;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public abstract class BaseHibernateDao<T> extends AbstractDAO<T> {
	
	public BaseHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public abstract T create(T obj);

    public abstract Optional<T> findById(Long id);

    public abstract Optional<T> updateById(Long id, T update);

    public abstract Optional<T> deleteById(Long id);
}
