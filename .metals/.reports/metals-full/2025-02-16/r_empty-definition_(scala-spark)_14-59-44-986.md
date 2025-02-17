error id: org/apache/hadoop/shaded/org/eclipse/jetty/websocket/common/frames/DataFrame#
file://<WORKSPACE>/src/main/scala/example/Main.scala
empty definition using pc, found symbol in pc: org/apache/hadoop/shaded/org/eclipse/jetty/websocket/common/frames/DataFrame#
empty definition using semanticdb
|empty definition using fallback
non-local guesses:
	 -org/apache/hadoop/shaded/org/eclipse/jetty/websocket/common/frames/DataFrame#
	 -DataFrame#
	 -scala/Predef.DataFrame#

Document text:

```scala
package example

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.hadoop.shaded.org.eclipse.jetty.websocket.common.frames.DataFrame

object Main {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("spark-example")
    val sc = new SparkContext(conf)

    // Set the log level to WARN
    sc.setLogLevel("WARN")

    val spark = SparkSession
      .builder()
      .appName("spark-example")
      .master("local[*]")
      .config("spark.drive.bindAddress", "127.0.0.1")
      .getOrCreate();

    val df: DataFrame = spark.read
      .options(
        Map(
          "header" -> "true",
          "inferSchema" -> "true"
        )
      )
      .csv("data/AAPL.csv");

    df.show()
    df.printSchema()
  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: org/apache/hadoop/shaded/org/eclipse/jetty/websocket/common/frames/DataFrame#