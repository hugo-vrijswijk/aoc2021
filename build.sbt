ThisBuild / scalaVersion := "3.1.0"

lazy val root = (project in file(".")).settings(
  name := "advent-of-code",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect"         % "3.3.4",
    "org.typelevel" %% "cats-parse"          % "0.3.6",
    "co.fs2"        %% "fs2-core"            % "3.2.4",
    "co.fs2"        %% "fs2-io"              % "3.2.4",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
  )
)
