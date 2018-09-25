name := """TweetApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice

libraryDependencies += javaJdbc
libraryDependencies ++= Seq(
  ws
)
libraryDependencies += jdbc
// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.197"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += filters

libraryDependencies += javaJpa

libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0"

libraryDependencies += javaWs % "test"

libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.5.Final"

libraryDependencies ++= Seq(
  "org.mongodb" % "mongo-java-driver" % "3.8.1"
)

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.16"
//libraryDependencies += "org.mockito" % "mockito-core" % "2.21.0" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"