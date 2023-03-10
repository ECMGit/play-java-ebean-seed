# Play!Java Ebean Seed
![GitHub](https://img.shields.io/github/license/ECMGit/play-java-ebean-seed)

update date: 03/09/2023  
Author: Junhao Shen

OS support:
- MacOS Ventura 13.0 Apple M2 Chips
- Ubuntu 20.04

## Prerequisite:
- Java version: 11.0.18
- Scala Version: 2.13.10
- Play! Framework version: 2.18.19
- sbt version: 1.7.2
- Docker

[Version compatibility table](https://docs.scala-lang.org/overviews/jdk-compatibility/overview.html)

## Run in Terminal
Make sure the prerequisite has been set up:
check SBT installation: `sbt --version`
if your installed sbt version is different, the output maybe:
```
sbt version in this project: 1.7.2
sbt script version: 1.8.2
```  

check JDK version: `java --version`
> Multiple Java versions?  
run `update-java-alternatives --list` check your installed JDKs, and set JDK 11 as default

## Run in Intellij IDEA
1. Open the project, and wait editor complete indexing
2. go to **File** --> **Project Structure** -->  set **SDK** to JDK 11(if you haven't install it, click **Add JDK** --> **Download JDK**)
3. Go to right-top **Edit Configuration** --> Add **Play 2 APP** --> set a name --> set port number(default is 9000)


## Option: Launch your database in Docker
In our project, we use MySQL as the our backend database, you can set up multiple datasource, just ensure install their connector dependencies in **build.sbt** and configure in **conf/application.conf**  
e.g creat a mysql8 database name test:  
`docker run -itd --name ebean -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345 mysql:8.0`

In **conf/application.conf**, we use ebean_test as default database name, you need to create a database called "ebean_test" in your docker container

## Test your RESTful API
run and check your database if the data has been put
e.g. use 9000 as localhost port number
```
curl -X POST -H "Content-Type: application/json" \
-d '{"id":0, "username": "smucs_sde", "password": "play!cs@smu", "firstName":"Junhao", "lastName":"Shen"}' \
http://localhost:9000/signup
```


## Plugins
(everthing is already configured, for more information, please check **build.sbt** and **conf/application.conf**)
- ORM: [PlayEbean](https://www.playframework.com/documentation/2.6.x/JavaEbean)
- Managing database evolutions: [evolutions](https://www.playframework.com/documentation/2.8.x/Evolutions)
- Configuring JDBC connection pools: [javaJdbc](https://www.playframework.com/documentation/2.8.19/AccessingAnSQLDatabase)

- Configuring the JDBC Driver dependency(e.g. mysql5): `"mysql" % "mysql-connector-java" % "5.1.41"` 
(if you use MacOS with Apple Chips, mysql8 is recommended)

- Dependency Injection: [guice](https://www.playframework.com/documentation/2.8.x/JavaDependencyInjection)

- Calling REST APIs with Play WS: [javaWS](https://www.playframework.com/documentation/2.8.x/JavaWS)

- The Play cache API: [ehcache](https://www.playframework.com/documentation/2.8.x/JavaCache)

## Project Layout
```
├── app                                 # write your code under this directory
│   ├── controllers                     # controllders module
│   │   ├── HomeController.java         # Home Controller
│   │   └── UserController.java         # User Controller, for handling request for user object
│   ├── models                          # data accessing module
│   │   └── User.java                   # The data Model map to table in your database
│   └── views                           
│       └── index.scala.html            # testing purpose, for backend, this file is not required
├── build.sbt                           # declare your dependencies, project name, scala versions here
├── conf
│   ├── application.conf                # configure your database parameters, port number, etc.
│   ├── evolutions                      # auto sql script by evolutions plugin, you don't have to touch this folder
│   │   └── default
│   │       └── 1.sql
│   ├── logback.xml
│   └── routes                          # define your RESTiful API here
├── logs
│   └── application.log
├── project
│   ├── build.properties
│   ├── plugins.sbt                     # preloading plugin/dependencies, we add PlayFramework & PlayEbean here
│   └── project
├── public                              # your public asset folder
│   ├── images
│   │   └── favicon.png
└── test                                # writing your test function under this folder
    └── controllers
        └── HomeControllerTest.java
```


## Additional Resources:
[RESTful API with Play in Java](https://www.baeldung.com/rest-api-with-play)
