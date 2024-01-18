package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ParquetOrcBenchmark {

    private SparkSession spark;
    private Dataset<Row> rawDF;
    @Setup
    public void setup() {
        this.spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .master("local")
                .getOrCreate();

        String prefixPathToDataset = "./src/test/java/org/example/data";
        this.rawDF = this.spark.read().format("csv").option("header", true)
                .load(prefixPathToDataset + "/size1kb.csv");
    }
    @Benchmark
    public void parquetBenchmark() {
        this.rawDF.write().mode(SaveMode.Overwrite)
                .parquet("./src/main/java/org/example/results/output_parquet/1kb");
    }

    @Benchmark
    public void orcBenchmark() {
        rawDF.write().mode(SaveMode.Overwrite)
                .format("orc")
                .save("./src/main/java/org/example/results/output_orc/1kb");
    }

    @TearDown
    public void tearDown() {
        this.spark.stop();
    }
}