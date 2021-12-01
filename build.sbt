ThisBuild / scalaVersion := "3.1.0"

lazy val root = (project in file(".")).settings(
  name := "advent-of-code",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect"         % "3.3.0",
    "co.fs2"        %% "fs2-core"            % "3.2.2",
    "co.fs2"        %% "fs2-io"              % "3.2.2",
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.6" % Test
  )
)
