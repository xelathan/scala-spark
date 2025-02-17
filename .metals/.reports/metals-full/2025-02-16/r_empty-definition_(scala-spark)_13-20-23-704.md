error id: 
file://<WORKSPACE>/src/main/scala/example/Main.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package example

import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    SparkSession.builder()
    .appName("spark-example")
    .master("local[*]");
  }
}
```

#### Short summary: 

empty definition using pc, found symbol in pc: 