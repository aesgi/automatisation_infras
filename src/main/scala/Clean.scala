import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, dayofmonth, month, year}

object Clean {
  def main(args: Array[String]) = {
    val spark = SparkSession.builder
      .appName("Circulation")
      //.config("spark.master", "local") // Uncomment to run locally
      .getOrCreate();

    val inputFile = args(0)
    val outputFile = args(1)

    // lire son fichier d'input
    val df = spark.read.csv(inputFile)

    // ajouter 3 colonnes à votre dataframe pour l'année, le mois et le jour
    val CleanDF = df.select(col("iu_ac"),
      year(col("date")).alias("year"),
      month(col("date")).alias("month"),
      dayofmonth(col("date")).alias("day"))

    CleanDF.write.parquet(outputFile)
  }
}
