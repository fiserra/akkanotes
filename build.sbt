name := "akkanotes"

version := "1.0"

scalaVersion := "2.11.4"


resolvers ++=
  Seq("repo" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= {
  val akkaVersion = "2.3.7"
  val sprayVersion = "1.3.2"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.2.6",
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "org.scalatest" %% "scalatest" % "2.2.0"
  )
}