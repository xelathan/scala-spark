error id: org/apache/spark/sql/functions.lit().
file://<WORKSPACE>/src/main/scala/example/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol org/apache/spark/sql/functions.lit().
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package example

import org.apache.spark.sql.{DataFrame, SparkSession, SparkContext, SparkConf}
import org.apache.spark.sql.functions.{col, lit}
import org.apache.spark.sql.types.StringType

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

    df.select("Date", "Open", "Close").show()
    val applyColClose = df.apply("Close")
    val colOpen = col("Open")

    import spark.implicits._
    val implicitDate = $"Date"

    df.select(implicitDate, colOpen, applyColClose).show()

    val addedCol = (colOpen + (2.0)).name("Open Added 2.0")

    val stringAddedCol = addedCol.cast(StringType)

    val literalTest = lit(-1.0)

    df.select(colOpen, addedCol).show()

    df.select(colOpen, applyColClose, implicitDate, literalTest)
      .filter(colOpen > applyColClose)
      .show()

    println(df.count())

  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 