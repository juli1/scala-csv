lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.gunnm.scala",
      scalaVersion := "2.12.4"
    )),
    name := "scalatest-example"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
