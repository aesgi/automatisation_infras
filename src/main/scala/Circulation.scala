import org.apache.spark.sql.SparkSession

object Circulation {
  def main(args: Array[String]) = {
    val spark = SparkSession.builder
      .appName("Circulation")
      //.config("spark.master", "local")  // Uncomment to run locally
      .getOrCreate();

    val inputFile = args(0)
    val joinFile = args(1)
    val outputFile = args(2)


    // TODO : lire son fichier d'input et son fichier de jointure
    val df = spark.read.csv(inputFile)
    val joinDf = spark.read.parquet(joinFile)

    val df_joinDf = df.join(joinDf,joinDf("iu_ac") ===  df("iu_ac"),"inner")
    val outputDf = df_joinDf.groupBy("k").mean("k")

    outputDf.write.csv(outputFile)

    // terminate spark context
    spark.stop()
  }
}

