error id: java/lang/String#toLowerCase(+1).
file://<WORKSPACE>/src/main/scala/example/Main.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol java/lang/String#toLowerCase(+1).
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package example

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, lit, expr, current_timestamp}
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

    // df.select("Date", "Open", "Close").show()
    // val applyColClose = df.apply("Close")
    // val colOpen = col("Open")

    // import spark.implicits._
    // val implicitDate = $"Date"

    // df.select(implicitDate, colOpen, applyColClose).show()

    // val addedCol = (colOpen + (2.0)).name("Open Added 2.0")

    // val stringAddedCol = addedCol.cast(StringType)

    // val literalTest = lit(-1.0)

    // df.select(colOpen, addedCol).show()

    // df.select(colOpen, applyColClose, implicitDate, literalTest)
    //   .filter(colOpen > applyColClose)
    //   .show()

    // println(df.count())

    // val timestampCol = expr(
    //   "cast(current_timestamp() as string) as timestampExp"
    // )
    // val timestampFuncCol =
    //   current_timestamp().cast(StringType).as("timestampFunction")

    // df.select(timestampCol, timestampFuncCol).show()

    // df.selectExpr("cast(Date as string)", "Open + 1.0", "current_timestamp()")
    //   .show()

    // df.createTempView("myDf")
    // spark
    //   .sql("""
    // SELECT * FROM myDf WHERE Close > Open
    // """)
    //   .show()

    val renamedCols = List(
      col("Date").as("date"),
      col("Open").as("open"),
      col("High").as("high"),
      col("Close").as("close"),
      col("Low").as("low"),
      col("Adj close").as("adjclose"),
      col("Volume").as("volume")
    )

    df.select(renamedCols: _*).show()

    df.columns.map(c => col(c).as(c.toLowerCase()))
  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 