ThisBuild / scalaVersion := "2.13.3"
ThisBuild / organization := "dev.usommerl"
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.4.4"

val v = new {
  val catsEffect = "2.2.0"
  val odin       = "0.9.1"
}

lazy val graal2928 = project
  .in(file("."))
  .enablePlugins(sbtdocker.DockerPlugin, GraalVMNativeImagePlugin)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.valskalla" %% "odin-core"   % v.odin,
      "org.typelevel"        %% "cats-effect" % v.catsEffect
    ),
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    docker / dockerfile := NativeDockerfile(file("Dockerfile")),
    docker / imageNames := Seq(ImageName(s"ghcr.io/usommerl/${name.value}:latest")),
    assembly / assemblyMergeStrategy := {
      case x if x.endsWith("module-info.class") => MergeStrategy.discard
      case x                                    =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
