package utils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class ParquetOrcReader {

    private SparkSession setup(){
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .master("local")
                .getOrCreate();
        return spark;
    }

    // "src/main/java/data/data.csv"
    public Dataset<Row> readParquet(String filePath) {
        return setup().read().parquet(filePath);
    }

    public Dataset<Row> readOrc(String filePath) {
        return setup().read().orc(filePath);
    }
}
