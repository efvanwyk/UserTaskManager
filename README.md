# User Task Manager
This application manages users and user tasks.

# Running The Application
To test the application, run the following commands:

* To package the example, run:
```sh
mvn package
```

* To run the server, run:
```sh
java -jar target/original-user-task-manager-2.0-SNAPSHOT.jar
```

### The following command line scripts are available for convenience:
* To package the example, run:
```sh
build_package.cmd
```

* To run the server, run:
```sh
run_server.cmd
```

* To build and run the application, run:
```sh
build_and_run.cmd
```

# Using The Application
See the [Postman](http://www.getpostman.com/) chrome plugin, or alternatively, run any of the following curl commands:
#### Create user
```sh
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"jsmith","first_name" : "John", "last_name" : "Smith"}' http://localhost:8080/api/user
```

#### Update user
```
curl -i -H "Content-Type: application/json" -X PUT -d '{"first_name" : "John", "last_name" : "Doe"}' http://localhost:8080/api/user/{id}
```

#### List all users
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user
```

#### Get User info
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{id}
```

#### Create Task
```sh
curl -i -H "Content-Type: application/json" -X POST -d '{"name":"My task","description" : "Description of task", "date_time" : "2016-05-25 14:25:00"}' http://localhost:8080/api/user/{user_id}/task
```

#### Update Task
```sh
curl -i -H "Content-Type: application/json" -X PUT -d '{"name":"My updated task"}' http://localhost:8080/api/user/{user_id}/task/{task_id}
```

#### Delete Task
```sh
curl -i -H "Content-Type: application/json" -X DELETE http://localhost:8080/api/user/{user_id}/task/{task_id}
```

#### Get Task Info
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{user_id}/task/{task_id}
```

#### List all tasks for a user

```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{user_id}/task
```

### The following command line scripts make use of some these curl commands and are available for convenience
```sh
curl_cmd/DeleteTask.cmd
curl_cmd/DeleteUser.cmd
curl_cmd/GetTaskForUser.cmd
curl_cmd/GetTasksForUser.cmd
curl_cmd/GetUser.cmd
curl_cmd/GetUsers.cmd
```

### The H2 backing database can be accessed through the following details:
```sh
URL: localhost:8080\h2
Driver Class: org.h2.Driver
JDBC URL 8: jdbc:h2:file:~/h2/user_task_manager_db
User Name: sa
Password:
```