name := "core"

//version := "2017.1.16"

enablePlugins(ScalaJSPlugin)

val scala211 = "2.11.8"

val scala212 = "2.12.1"

scalaVersion := scala212

crossScalaVersions := Seq(scala211, scala212)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions"
)

//bintray
resolvers += Resolver.jcenterRepo

organization := "scalajs-react-universe"

licenses += ("Apache-2.0", url(
  "http://www.opensource.org/licenses/apache2.0.php"))

bintrayOrganization := Some("scalajs-react-universe")

bintrayRepository := "maven"

bintrayVcsUrl := Some("git@github.com:scalajs-react-universe/core.git")

publishArtifact in Test := false

//Test
scalaJSModuleKind := ModuleKind.CommonJSModule
resolvers += Resolver.bintrayRepo("scalajs-react-universe", "maven")
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % Test
libraryDependencies += "scalajs-react-universe" %%% "enzyme" % "2017.2.0-SNAPSHOT" % Test
libraryDependencies += "scalajs-react-universe" %%% "macros" % "2017.2.0-SNAPSHOT" % Test
//scalaJSStage in Global := FastOptStage
scalaJSStage in Global := FullOptStage
