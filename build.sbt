name := "RPG"

version := "0.1"

scalaVersion := "2.12.3"

// project description
description := "RPG"

// Enables publishing to maven repo
publishMavenStyle := true

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

logBuffered in Test := false

conflictManager := ConflictManager.strict

publishArtifact in Test := true

mainClass in (Compile,run) := Some("RPG.Main")

// library dependencies. (orginization name) % (project name) % (version)
libraryDependencies ++= Seq(
   "junit" % "junit" % "4.12" % "test",
  "commons-collections" % "commons-collections" % "3.2.2"
)

