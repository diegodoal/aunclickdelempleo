import sbt._


object ApplicationBuild extends Build {

    val appName         = ""
    val appVersion      = ""

	val appDependencies = Seq(
	    "net.vz.mongodb.jackson" %% "play-mongo-jackson-mapper" % "1.1.0" 
	)
}