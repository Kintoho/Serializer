package utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;


public class ParquetOrcWriter {

    private Dataset<Row> setup(String filePath) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .master("local")
                .getOrCreate();

        //String filePath = "src/main/java/data/data.csv";
        return spark.read().format("csv").option("header", true).load(filePath);
    }

    // DIR: "src/main/java/results/parquet/big"
    public void toParquet(String filePath, String destinationDir){
        Dataset<Row> df = setup(filePath);
        df.write().mode(SaveMode.Overwrite).parquet(destinationDir);
    }

    // DIR: "src/main/java/results/orc/big"
    public void toOrc(String filePath, String destinationDir){
        Dataset<Row> df = setup(filePath);
        df.write().mode(SaveMode.Overwrite).format("orc").save(destinationDir);
    }
}