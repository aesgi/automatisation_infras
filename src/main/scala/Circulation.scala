import org.apache.spark.sql.SparkSession

object Circulation {
  def main(args: Array[String]) = {
    val spark = SparkSession.builder
      .appName("Circulation")
      //.config("spark.master", "local")  // Uncomment to run locally
      .getOrCreate();

    println("************")
    println("************")
    println("Hello, world!")
    val rdd = spark.sparkContext.parallelize(Array(1 to 10))
    rdd.count()
    println("************")
    println("************")

    // terminate spark context
    spark.stop()
  }
}

