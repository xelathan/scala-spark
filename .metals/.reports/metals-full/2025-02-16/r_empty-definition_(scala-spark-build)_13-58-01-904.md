error id: sbt/Keys.libraryDependencies.
file://<WORKSPACE>/build.sbt
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol sbt/Keys.libraryDependencies.
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
val toolkitV = "0.5.0"
val toolkit = "org.scala-lang" %% "toolkit" % toolkitV
val toolkitTest = "org.scala-lang" %% "toolkit-test" % toolkitV

ThisBuild / scalaVersion := "2.13.16"

libraryDependencies += toolkit
libraryDependencies += (toolkitTest % Test)

val sparkVersion = "3.5.4"
val hadoopVersion = "3.4.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

```

#### Short summary: 

empty definition using pc, found symbol in pc: 