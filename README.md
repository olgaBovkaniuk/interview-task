# Log manager project

## 1. How to install

### 1.1 Setup `WEB-APP` application properties

location:
```
/logmanager-web-app/src/main/resources/application.properties
```

required properties (by default is):

```
server.port=8085
spring.datasource.url=jdbc:mysql://localhost:3306/log_manager?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```

### 1.2 Setup `UPDATER` application properties

location:
```
/updater/src/main/resources/app.properties
```

requred properties:
```
log.files.dir.path=<path to log files dir>
```

### 1.3 Go to `log-manager` dir and execute command
```
mvn clean install
```

## 2. Project diagram

![image](Project_diagram.png)
