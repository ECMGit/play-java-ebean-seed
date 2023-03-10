scalaVersion := "2.13.10"
version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava,PlayEbean)
  .settings(
    name := """play-java-ebean-seed""",
    libraryDependencies ++= Seq(
      evolutions,
      jdbc,
      javaWs,
      ehcache,
      guice
    )
  )

// https://mvnrepository.com/artifact/javax.json/javax.json-api


libraryDependencies += "com.mysql" % "mysql-connector-j" % "8.0.32"

// for fix issue of JDK 11
libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.3.1"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.1"

libraryDependencies ++= Seq(specs2 % Test )
