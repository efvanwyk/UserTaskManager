# User Task Manager
This application manages users and user tasks.

# Running The Application

To test the application, run the following commands:

* To package the example, run:
```sh
mvn package
```

* To setup the h2 database, run:
```sh
java -jar target/user-task-manager-1.0-SNAPSHOT.jar db migrate usertaskmanager.yml
```

* To run the server, run:
```sh
java -jar target/user-task-manager-1.0-SNAPSHOT.jar server usertaskmanager.yml
```

# Using The Application
#### Create user
```sh
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"jsmith","first_name" : "John", "last_name" : "Smith"} 'http://hostname/api/user
```

#### Update user
```
curl -i -H "Content-Type: application/json" -X PUT -d '{"first_name" : "John", "last_name" : "Doe"}' http://hostname/api/user/{id}
```

#### List all users
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://hostname/api/user
```

#### Get User info
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://hostname/api/user/{id}
```

#### Create Task
```sh
curl -i -H "Content-Type: application/json" -X POST -d '{"name":"My task","description" : "Description of task", "date_time" : "2016-05-25 14:25:00"}' http://hostname/api/user/{user_id}/task
```

#### Update Task
```sh
curl -i -H "Content-Type: application/json" -X PUT -d '{"name":"My updated task"}' http://hostname/api/user/{user_id}/task/{task_id}
```

#### Delete Task
```sh
curl -i -H "Content-Type: application/json" -X DELETE http://hostname/api/user/{user_id}/task/{task_id}
```

#### Get Task Info
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://hostname/api/user/{user_id}/task/{task_id}
```

#### List all tasks for a user

```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://hostname/api/user/{user_id}/task
