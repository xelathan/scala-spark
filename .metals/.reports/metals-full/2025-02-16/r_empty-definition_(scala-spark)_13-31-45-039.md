error id: org/apache/spark/sql/DataFrameReader#option(+1).
file://<WORKSPACE>/src/main/scala/example/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol org/apache/spark/sql/DataFrameReader#option(+1).
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package example

import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("spark-example")
      .master("local[*]")
      .getOrCreate();

    val df = spark.read.option("header", value = true).csv("data/APPL.csv");

    df.show()
  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 