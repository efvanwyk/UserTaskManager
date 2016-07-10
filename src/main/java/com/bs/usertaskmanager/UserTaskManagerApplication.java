package com.bs.usertaskmanager;

import com.bs.usertaskmanager.UserTaskManagerConfiguration;
import com.bs.usertaskmanager.core.Task;
import com.bs.usertaskmanager.core.User;
import com.bs.usertaskmanager.db.TaskDao;
import com.bs.usertaskmanager.db.UserDao;
import com.bs.usertaskmanager.resources.TaskResource;
import com.bs.usertaskmanager.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class UserTaskManagerApplication extends Application<UserTaskManagerConfiguration> {
    public static void main(String[] args) throws Exception {
        new UserTaskManagerApplication().run(args);
    }
    
	private final HibernateBundle<UserTaskManagerConfiguration> hibernateBundle = 
			new HibernateBundle<UserTaskManagerConfiguration>(
			User.class,
			Task.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(UserTaskManagerConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};
    
    @Override
    public String getName()
    {
    	return APP_NAME;
    }
    
    @Override
    public void initialize(Bootstrap<UserTaskManagerConfiguration> bootstrap) {
    	// Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new MigrationsBundle<UserTaskManagerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(UserTaskManagerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

	@Override
	public void run(UserTaskManagerConfiguration configuration, Environment environment) throws Exception {
		final UserDao userDao = new UserDao(hibernateBundle.getSessionFactory());
		final TaskDao taskDao = new TaskDao(hibernateBundle.getSessionFactory());

		environment.jersey().register(new UserResource(userDao));
		environment.jersey().register(new TaskResource(taskDao));

		// TODO: setup quartz scheduler
	}
	
	public static final String APP_NAME = "user-task-manager";
}
