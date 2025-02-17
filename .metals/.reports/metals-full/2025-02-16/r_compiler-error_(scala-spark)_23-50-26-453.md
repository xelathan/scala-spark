error id: E74485B2F15E0B65A9684C39A8E297E8
file://<WORKSPACE>/src/main/scala/example/Main.scala
### java.lang.IndexOutOfBoundsException: -1 is out of bounds (min 0, max 2)

occurred in the presentation compiler.



action parameters:
uri: file://<WORKSPACE>/src/main/scala/example/Main.scala
text:
```scala
package example

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
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

    df.select(colOpen, addedCol).show()

    df.select(colOpen, applyColClose, implicitDate)
    .filter(col)
  }
}

```


presentation compiler configuration:
Scala version: 2.13.14
Classpath:
<WORKSPACE>/.bloop/scala-spark/bloop-bsp-clients-classes/classes-Metals-mSJ-oBTxRjCZW2kDYfdOrA== [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.10.3/semanticdb-javac-0.10.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.14/scala-library-2.13.14.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/toolkit_2.13/0.5.0/toolkit_2.13-0.5.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-core_2.13/3.5.4/spark-core_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-sql_2.13/3.5.4/spark-sql_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client4/core_2.13/4.0.0-M16/core_2.13-4.0.0-M16.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client4/upickle_2.13/4.0.0-M16/upickle_2.13-4.0.0-M16.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/upickle_2.13/3.3.1/upickle_2.13-3.3.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/os-lib_2.13/0.10.3/os-lib_2.13-0.10.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parallel-collections_2.13/1.0.4/scala-parallel-collections_2.13-1.0.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/avro/avro/1.11.2/avro-1.11.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/avro/avro-mapred/1.11.2/avro-mapred-1.11.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/twitter/chill_2.13/0.10.0/chill_2.13-0.10.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/twitter/chill-java/0.10.0/chill-java-0.10.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/xbean/xbean-asm9-shaded/4.23/xbean-asm9-shaded-4.23.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/hadoop/hadoop-client-api/3.3.4/hadoop-client-api-3.3.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/hadoop/hadoop-client-runtime/3.3.4/hadoop-client-runtime-3.3.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-launcher_2.13/3.5.4/spark-launcher_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-kvstore_2.13/3.5.4/spark-kvstore_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-network-common_2.13/3.5.4/spark-network-common_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-network-shuffle_2.13/3.5.4/spark-network-shuffle_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-unsafe_2.13/3.5.4/spark-unsafe_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-common-utils_2.13/3.5.4/spark-common-utils_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/curator/curator-recipes/2.13.0/curator-recipes-2.13.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/zookeeper/zookeeper/3.6.3/zookeeper-3.6.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/servlet/jakarta.servlet-api/4.0.3/jakarta.servlet-api-4.0.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-codec/commons-codec/1.16.1/commons-codec-1.16.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-compress/1.23.0/commons-compress-1.23.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-math3/3.6.1/commons-math3-3.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-text/1.10.0/commons-text-1.10.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-io/commons-io/2.16.1/commons-io-2.16.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-collections4/4.4/commons-collections4-4.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/ning/compress-lzf/1.1.2/compress-lzf-1.1.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/xerial/snappy/snappy-java/1.1.10.5/snappy-java-1.1.10.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/lz4/lz4-java/1.8.0/lz4-java-1.8.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/luben/zstd-jni/1.5.5-4/zstd-jni-1.5.5-4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/roaringbitmap/RoaringBitmap/0.9.45/RoaringBitmap-0.9.45.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.13/2.1.0/scala-xml_2.13-2.1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-collection-compat_2.13/2.7.0/scala-collection-compat_2.13-2.7.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.14/scala-reflect-2.13.14.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-jackson_2.13/3.7.0-M11/json4s-jackson_2.13-3.7.0-M11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/core/jersey-client/2.40/jersey-client-2.40.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/core/jersey-common/2.40/jersey-common-2.40.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/core/jersey-server/2.40/jersey-server-2.40.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/containers/jersey-container-servlet/2.40/jersey-container-servlet-2.40.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/containers/jersey-container-servlet-core/2.40/jersey-container-servlet-core-2.40.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/inject/jersey-hk2/2.40/jersey-hk2-2.40.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-all/4.1.96.Final/netty-all-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-epoll/4.1.96.Final/netty-transport-native-epoll-4.1.96.Final-linux-x86_64.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-epoll/4.1.96.Final/netty-transport-native-epoll-4.1.96.Final-linux-aarch_64.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-kqueue/4.1.96.Final/netty-transport-native-kqueue-4.1.96.Final-osx-aarch_64.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-kqueue/4.1.96.Final/netty-transport-native-kqueue-4.1.96.Final-osx-x86_64.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/clearspring/analytics/stream/2.9.6/stream-2.9.6.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-core/4.2.19/metrics-core-4.2.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-jvm/4.2.19/metrics-jvm-4.2.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-json/4.2.19/metrics-json-4.2.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-graphite/4.2.19/metrics-graphite-4.2.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-jmx/4.2.19/metrics-jmx-4.2.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-scala_2.13/2.15.2/jackson-module-scala_2.13-2.15.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/ivy/ivy/2.5.1/ivy-2.5.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/oro/oro/2.0.8/oro-2.0.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/razorvine/pickle/1.3/pickle-1.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/sf/py4j/py4j/0.10.9.7/py4j-0.10.9.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-tags_2.13/3.5.4/spark-tags_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-crypto/1.1.0/commons-crypto-1.1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/rocksdb/rocksdbjni/8.3.2/rocksdbjni-8.3.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/univocity/univocity-parsers/2.9.1/univocity-parsers-2.9.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-sketch_2.13/3.5.4/spark-sketch_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-catalyst_2.13/3.5.4/spark-catalyst_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/orc/orc-core/1.9.5/orc-core-1.9.5-shaded-protobuf.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/orc/orc-mapreduce/1.9.5/orc-mapreduce-1.9.5-shaded-protobuf.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/hive/hive-storage-api/2.8.1/hive-storage-api-2.8.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/parquet/parquet-column/1.13.1/parquet-column-1.13.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/parquet/parquet-hadoop/1.13.1/parquet-hadoop-1.13.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/model/core_2.13/1.7.10/core_2.13-1.7.10.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/shared/core_2.13/1.3.19/core_2.13-1.3.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/shared/ws_2.13/1.3.19/ws_2.13-1.3.19.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/softwaremill/sttp/client4/json-common_2.13/4.0.0-M16/json-common_2.13-4.0.0-M16.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/ujson_2.13/3.3.1/ujson_2.13-3.3.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/upack_2.13/3.3.1/upack_2.13-3.3.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/upickle-implicits_2.13/3.3.1/upickle-implicits_2.13-3.3.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/geny_2.13/1.1.1/geny_2.13-1.1.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.7/slf4j-api-2.0.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/avro/avro-ipc/1.11.2/avro-ipc-1.11.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/esotericsoftware/kryo-shaded/4.0.2/kryo-shaded-4.0.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/fusesource/leveldbjni/leveldbjni-all/1.8/leveldbjni-all-1.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/crypto/tink/tink/1.9.0/tink-1.9.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/2.0.7/jul-to-slf4j-2.0.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/2.0.7/jcl-over-slf4j-2.0.7.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-slf4j2-impl/2.20.0/log4j-slf4j2-impl-2.20.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.20.0/log4j-api-2.20.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.20.0/log4j-core-2.20.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-1.2-api/2.20.0/log4j-1.2-api-2.20.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/curator/curator-framework/2.13.0/curator-framework-2.13.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/zookeeper/zookeeper-jute/3.6.3/zookeeper-jute-3.6.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/yetus/audience-annotations/0.13.0/audience-annotations-0.13.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/roaringbitmap/shims/0.9.45/shims-0.9.45.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-core_2.13/3.7.0-M11/json4s-core_2.13-3.7.0-M11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/ws/rs/jakarta.ws.rs-api/2.1.6/jakarta.ws.rs-api-2.1.6.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/external/jakarta.inject/2.6.1/jakarta.inject-2.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/osgi-resource-locator/1.0.3/osgi-resource-locator-1.0.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/validation/jakarta.validation-api/2.0.2/jakarta.validation-api-2.0.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/hk2-locator/2.6.1/hk2-locator-2.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/javassist/javassist/3.29.2-GA/javassist-3.29.2-GA.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-buffer/4.1.96.Final/netty-buffer-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-codec/4.1.96.Final/netty-codec-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-codec-http/4.1.96.Final/netty-codec-http-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-codec-http2/4.1.96.Final/netty-codec-http2-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-codec-socks/4.1.96.Final/netty-codec-socks-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-common/4.1.96.Final/netty-common-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-handler/4.1.96.Final/netty-handler-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-unix-common/4.1.96.Final/netty-transport-native-unix-common-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-handler-proxy/4.1.96.Final/netty-handler-proxy-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-resolver/4.1.96.Final/netty-resolver-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport/4.1.96.Final/netty-transport-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-classes-epoll/4.1.96.Final/netty-transport-classes-epoll-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-classes-kqueue/4.1.96.Final/netty-transport-classes-kqueue-4.1.96.Final.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-sql-api_2.13/3.5.4/spark-sql-api_2.13-3.5.4.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/codehaus/janino/janino/3.1.9/janino-3.1.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/codehaus/janino/commons-compiler/3.1.9/commons-compiler-3.1.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/datasketches/datasketches-java/3.3.0/datasketches-java-3.3.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/orc/orc-shims/1.9.5/orc-shims-1.9.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/airlift/aircompressor/0.27/aircompressor-0.27.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jetbrains/annotations/17.0.0/annotations-17.0.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/threeten/threeten-extra/1.7.1/threeten-extra-1.7.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/parquet/parquet-common/1.13.1/parquet-common-1.13.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/parquet/parquet-encoding/1.13.1/parquet-encoding-1.13.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/parquet/parquet-format-structures/1.13.1/parquet-format-structures-1.13.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/parquet/parquet-jackson/1.13.1/parquet-jackson-1.13.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/upickle-core_2.13/3.3.1/upickle-core_2.13-3.3.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/tukaani/xz/1.9/xz-1.9.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/esotericsoftware/minlog/1.3.0/minlog-1.3.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/objenesis/objenesis/2.5.1/objenesis-2.5.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.19.6/protobuf-java-3.19.6.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/joda-time/joda-time/2.12.5/joda-time-2.12.5.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/curator/curator-client/2.13.0/curator-client-2.13.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-ast_2.13/3.7.0-M11/json4s-ast_2.13-3.7.0-M11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-scalap_2.13/3.7.0-M11/json4s-scalap_2.13-3.7.0-M11.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/external/aopalliance-repackaged/2.6.1/aopalliance-repackaged-2.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/hk2-api/2.6.1/hk2-api-2.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/hk2-utils/2.6.1/hk2-utils-2.6.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.13/2.3.0/scala-parser-combinators_2.13-2.3.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/antlr/antlr4-runtime/4.9.3/antlr4-runtime-4.9.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-vector/12.0.1/arrow-vector-12.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-memory-netty/12.0.1/arrow-memory-netty-12.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/datasketches/datasketches-memory/2.1.0/datasketches-memory-2.1.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/guava/16.0.1/guava-16.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-format/12.0.1/arrow-format-12.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-memory-core/12.0.1/arrow-memory-core-12.0.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.15.1/jackson-datatype-jsr310-2.15.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/flatbuffers/flatbuffers-java/1.12.0/flatbuffers-java-1.12.0.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-epoll/4.1.96.Final/netty-transport-native-epoll-4.1.96.Final.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb




#### Error stacktrace:

```
scala.collection.mutable.ArrayBuffer.apply(ArrayBuffer.scala:102)
	scala.reflect.internal.Types$Type.findMemberInternal$1(Types.scala:1030)
	scala.reflect.internal.Types$Type.findMember(Types.scala:1035)
	scala.reflect.internal.Types$Type.memberBasedOnName(Types.scala:661)
	scala.reflect.internal.Types$Type.nonPrivateMember(Types.scala:632)
	scala.tools.nsc.typechecker.Infer$Inferencer.followApply(Infer.scala:660)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$doTypedApply$3(Typers.scala:3632)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$doTypedApply$3$adapted(Typers.scala:3621)
	scala.reflect.internal.Symbols$Symbol.filter(Symbols.scala:2034)
	scala.tools.nsc.typechecker.Typers$Typer.preSelectOverloaded$1(Typers.scala:3621)
	scala.tools.nsc.typechecker.Typers$Typer.doTypedApply(Typers.scala:3651)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typed1$28(Typers.scala:5166)
	scala.tools.nsc.typechecker.Typers$Typer.silent(Typers.scala:727)
	scala.tools.nsc.typechecker.Typers$Typer.tryTypedApply$1(Typers.scala:5166)
	scala.tools.nsc.typechecker.Typers$Typer.normalTypedApply$1(Typers.scala:5254)
	scala.tools.nsc.typechecker.Typers$Typer.typedApply$1(Typers.scala:5267)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6205)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Typers$Typer.typedSelectOrSuperCall$1(Typers.scala:6359)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6206)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typed1$41(Typers.scala:5233)
	scala.tools.nsc.typechecker.Typers$Typer.silent(Typers.scala:713)
	scala.tools.nsc.typechecker.Typers$Typer.normalTypedApply$1(Typers.scala:5235)
	scala.tools.nsc.typechecker.Typers$Typer.typedApply$1(Typers.scala:5267)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6205)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:6339)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$9(Typers.scala:3539)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3539)
	scala.tools.nsc.typechecker.Typers$Typer.typedBlock(Typers.scala:2656)
	scala.tools.nsc.typechecker.Typers$Typer.typedOutsidePatternMode$1(Typers.scala:6179)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6215)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Typers$Typer.typedDefDef(Typers.scala:6525)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6167)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:6339)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$9(Typers.scala:3539)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3539)
	scala.tools.nsc.typechecker.Typers$Typer.typedTemplate(Typers.scala:2144)
	scala.tools.nsc.typechecker.Typers$Typer.typedModuleDef(Typers.scala:2020)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6169)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:6339)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$9(Typers.scala:3539)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3539)
	scala.tools.nsc.typechecker.Typers$Typer.typedPackageDef$1(Typers.scala:5844)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6171)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6261)
	scala.tools.nsc.typechecker.Analyzer$typerFactory$TyperPhase.apply(Analyzer.scala:125)
	scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:481)
	scala.tools.nsc.interactive.Global$TyperRun.applyPhase(Global.scala:1369)
	scala.tools.nsc.interactive.Global$TyperRun.typeCheck(Global.scala:1362)
	scala.tools.nsc.interactive.Global.typeCheck(Global.scala:680)
	scala.meta.internal.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:22)
	scala.meta.internal.pc.SimpleCollector.<init>(PcCollector.scala:342)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:19)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzycompute$1(PcSemanticTokensProvider.scala:19)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:19)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:73)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticTokens$1(ScalaPresentationCompiler.scala:194)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: -1 is out of bounds (min 0, max 2)