import NativePackagerKeys._

name := "akka-docker"

version := "2.3.4"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % version.value,
  "com.typesafe.akka" %% "akka-cluster" % version.value,
  "com.github.scopt" %% "scopt" % "3.2.0"
)

site.settings

site.includeScaladoc()

packageArchetype.java_server

maintainer := "Nepomuk Seiler"

packageSummary := s"Akka ${version.value} Server"

packageDescription := s"Akka ${version.value} Docker Image. Starts a simple akka server"