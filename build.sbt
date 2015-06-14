name := """mass-demand-app"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

//ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

lazy val root = (project in file(".")).enablePlugins(PlayScala)
 
libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws
)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "knockout" % "3.3.0",
  "nikias" % "massdemand_2.11" % "1.0-SNAPSHOT" withSources()  
)

EclipseKeys.createSrc := EclipseCreateSrc.All
