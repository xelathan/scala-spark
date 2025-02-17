val toolkitV = "0.5.0"
val toolkit = "org.scala-lang" %% "toolkit" % toolkitV
val toolkitTest = "org.scala-lang" %% "toolkit-test" % toolkitV

ThisBuild / scalaVersion := "2.13.16"

libraryDependencies += toolkit
libraryDependencies += (toolkitTest % Test)

val sparkVersion = "3.5.4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.hadoop" % "hadoop-client" % "3.3.4"
)

javaOptions += "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED"

fork := true
