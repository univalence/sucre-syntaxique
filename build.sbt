// Common configuration
inThisBuild(
  List(
    scalaVersion         := "3.1.1",
    version              := "0.1.0",
    description          := "Introduction sur l'utilisation du sucre syntaxique dans Scala",
    organization         := "io.univalence",
    organizationName     := "Univalence",
    organizationHomepage := Some(url("https://univalence.io/")),
    startYear            := Some(2022),
    developers := List(
      Developer(
        id    = "dylandoamaral",
        name  = "Dylan Do Amaral",
        email = "dylan@univalence.io",
        url   = url("https://github.com/dylandoamaral")
      )
    ),
    homepage := Some(url("https://github.com/univalence/sucre-syntaxique")),
    licenses := List("Apache-2.0" -> url("https://github.com/univalence/zio-spark/blob/master/LICENSE"))
  )
)

// Scalafix configuration
ThisBuild / scalafixScalaBinaryVersion := "2.13"
ThisBuild / semanticdbEnabled          := true
ThisBuild / semanticdbVersion          := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies ++= Seq("com.github.vovapolu" %% "scaluzzi" % "0.1.21")

// -- Main project settings
lazy val app =
  (project in file("."))
    .settings(
      name := "sucre-syntaxique",
      scalacOptions ~= fatalWarningsAsProperties,
      libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"
    )

/**
 * Don't fail the compilation for warnings by default, you can still
 * activate it using system properties (It should always be activated in
 * the CI).
 */
def fatalWarningsAsProperties(options: Seq[String]): Seq[String] =
  if (sys.props.getOrElse("fatal-warnings", "false") == "true") options
  else options.filterNot(Set("-Xfatal-warnings"))
