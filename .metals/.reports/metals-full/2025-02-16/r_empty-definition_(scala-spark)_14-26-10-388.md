error id: org/apache/spark/SparkConf#setAppName().
file://<WORKSPACE>/src/main/scala/example/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol org/apache/spark/SparkConf#setAppName().
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package example

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object Main {
  def main(args: Array[String]): Unit = {
    val conf = SparkConf()
      .setMaster("local[*]")
      .setAppName("spark-example")
    val sc = SparkContext(conf)

    // Set the log level to WARN
    sc.setLogLevel("WARN")

    val spark = SparkSession
      .builder()
      .appName("spark-example")
      .master("local[*]")
      .config("spark.drive.bindAddress", "127.0.0.1")
      .getOrCreate();

    val df = spark.read
      .option("header", value = true)
      .csv("data/AAPL.csv");

    df.show()
  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 