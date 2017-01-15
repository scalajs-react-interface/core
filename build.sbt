name := "core"

version := "2017.1.15"

enablePlugins(ScalaJSPlugin)

val scala211 = "2.11.8"

val scala212 = "2.12.0"

scalaVersion := scala211

crossScalaVersions := Seq(scala211,scala212)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions"
)


//bintray
resolvers += Resolver.jcenterRepo

organization := "scalajs-react-universe"

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

bintrayOrganization := Some("scalajs-react-universe")

bintrayRepository := "maven"

bintrayVcsUrl := Some("git@github.com:scalajs-react-universe/core.git")

publishArtifact in Test := false

//Test
resolvers += Resolver.bintrayRepo("scalajs-react-universe", "maven")
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % Test
//libraryDependencies += "scalajs-react-universe" %%% "enzyme" % "2017.1.0" % Test
scalaJSStage in Global := FastOptStage