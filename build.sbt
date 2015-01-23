import play.Project._

name := "webAdecco"

version := "1.0"


libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  jdbc,
  anorm,
  cache,
  "org.mongodb" % "mongo-java-driver" % "2.11.3",
  "org.mongodb" %% "casbah" % "2.6.4"
)   

playJavaSettings
