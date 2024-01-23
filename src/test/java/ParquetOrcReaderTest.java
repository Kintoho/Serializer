import org.junit.Test;
import utils.ParquetOrcReader;

public class ParquetOrcReaderTest {
    static ParquetOrcReader reader = new ParquetOrcReader();

    @Test
    public void readParquetOrcRandom() {
        for (int i = 0; i < 3; i++) {
            long startReadingParquet = System.currentTimeMillis();
            reader.readParquet("src/main/java/results/parquet/random/part-00000-39be6eed-4e76-44a4-8d4b-bb7159c9ffa6-c000.snappy.parquet");
            long endReadingParquet = System.currentTimeMillis();

            long startReadingOrc = System.currentTimeMillis();
            reader.readOrc("src/main/java/results/orc/random/part-00000-a6c6ce83-7e70-42b7-93c2-9f6b344a301e-c000.snappy.orc");
            long endReadingOrc = System.currentTimeMillis();

            System.out.println("ParquetRandomReading time: " + (endReadingOrc - startReadingOrc));
            System.out.println("OrcRandomReading time: " + (endReadingParquet - startReadingParquet));
        }
    }

    @Test
    public void readParquetOrcNotRandom() {
        for (int i = 0; i < 3; i++) {
            long startReadingParquet = System.currentTimeMillis();
            reader.readParquet("src/main/java/results/parquet/not_random/part-00000-d9d85b5d-7346-497c-ae6b-be733e7fc754-c000.snappy.parquet");
            long endReadingParquet = System.currentTimeMillis();

            long startReadingOrc = System.currentTimeMillis();
            reader.readOrc("src/main/java/results/orc/not_random/part-00000-390761c1-2323-4d08-ae6b-0dfda09a6109-c000.snappy.orc");
            long endReadingOrc = System.currentTimeMillis();

            System.out.println("ParquetNotRandomReading time: " + (endReadingOrc - startReadingOrc));
            System.out.println("OrcNotRandomReading time: " + (endReadingParquet - startReadingParquet));
        }
    }
}
