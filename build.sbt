ThisBuild / scalaVersion := "3.3.1"
ThisBuild / organization := "com.example"
ThisBuild / version := "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "order-interop",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.4",

      "org.http4s" %% "http4s-ember-server" % "0.23.25",
      "org.http4s" %% "http4s-dsl"          % "0.23.25",
      "org.http4s" %% "http4s-circe"        % "0.23.25",

      "io.circe" %% "circe-core"    % "0.14.6",
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser"  % "0.14.6"
    )
  )
