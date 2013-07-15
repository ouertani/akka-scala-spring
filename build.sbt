name := """akka-scala-spring"""

version := "0.1"

scalaVersion := "2.10.2"

resolvers += "SpringSource Milestone Repository" at "http://repo.springsource.org/milestone"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.0",
  "org.springframework.scala" % "spring-scala" % "1.0.0.M2",
  "javax.inject" % "javax.inject" % "1",
  "junit" % "junit" % "4.11" % "test",
  "org.specs2" %% "specs2" % "1.13" % "test"  ,
  "com.novocode" % "junit-interface" % "0.9" % "test->default"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

// Note: These settings are defaults for activator, and reorganize your source directories.
Seq(
  scalaSource in Compile <<= baseDirectory / "app",
  javaSource in Compile <<= baseDirectory / "app",
  sourceDirectory in Compile <<= baseDirectory / "app",
  scalaSource in Test <<= baseDirectory / "test",
  javaSource in Test <<= baseDirectory / "test",
  sourceDirectory in Test <<= baseDirectory / "test",
  resourceDirectory in Compile <<= baseDirectory / "conf"
)
