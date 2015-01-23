name := "webAdecco"

resolvers += Resolver.url("Playtch Repository", url("http://cuaqea.github.io/playtch-latch4play/releases/"))(Resolver.ivyStylePatterns)

version := "1.0-SNAPSHOT"


libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  jdbc,
  anorm,
  cache,
  "org.mongodb" % "mongo-java-driver" % "2.11.3",
  "org.mongodb" %% "casbah" % "2.6.4"
)   

play.Project.playJavaSettings
