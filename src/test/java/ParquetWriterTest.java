import org.apache.spark.sql.SaveMode;
import org.junit.Test;
import utils.ParquetOrcWriter;

public class ParquetWriterTest {
    static ParquetOrcWriter writer = new ParquetOrcWriter();

    @Test
    public void writeParquetOrcRandom() {
        for(int i = 0; i < 3; i++) {
            long startParquet = System.currentTimeMillis();
            writer.toParquet("src/main/java/data/data_random.csv", "src/main/java/results/parquet/random");
            long endParquet = System.currentTimeMillis();

            long startOrc = System.currentTimeMillis();
            writer.toOrc("src/main/java/data/data_random.csv", "src/main/java/results/orc/random");
            long endOrc = System.currentTimeMillis();

            System.out.println("ParquetRandom time: " + (endParquet - startParquet));
            System.out.println("OrcRandom time: " + (endOrc - startOrc));
        }
    }

    @Test
    public void writeParquetOrcNotRandom() {
        for(int i = 0; i < 3; i++) {
            long startParquet = System.currentTimeMillis();
            writer.toParquet("src/main/java/data/data_not_random.csv", "src/main/java/results/parquet/not_random");
            long endParquet = System.currentTimeMillis();

            long startOrc = System.currentTimeMillis();
            writer.toOrc("src/main/java/data/data_not_random.csv", "src/main/java/results/orc/not_random");
            long endOrc = System.currentTimeMillis();

            System.out.println("ParquetRandom time: " + (endParquet - startParquet));
            System.out.println("OrcRandom time: " + (endOrc - startOrc));
        }
    }

    @Test
    public void writeParquetOrcBig() {
        for(int i = 0; i < 3; i++) {
            long startParquet = System.currentTimeMillis();
            writer.toParquet("src/main/java/data/data.csv", "src/main/java/results/parquet/big");
            long endParquet = System.currentTimeMillis();

            long startOrc = System.currentTimeMillis();
            writer.toOrc("src/main/java/data/data.csv", "src/main/java/results/orc/big");
            long endOrc = System.currentTimeMillis();

            System.out.println("ParquetRandom time: " + (endParquet - startParquet));
            System.out.println("OrcRandom time: " + (endOrc - startOrc));
        }
    }
}