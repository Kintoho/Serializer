package generator;

import org.apache.commons.lang3.tuple.Pair;
import utils.ColumnTableGenerator;

import java.util.List;
import java.util.Map;

public class ColumnTableGeneratorRunner {
    private static final int size1kb = 1024;
    private static final int size1mb = 1024 * size1kb;
    private static final int size1gb = 1024 * size1mb;
    private static final int size2gb = 2 * size1mb;
    private static final int size3gb = 3 * size1gb;
    public static void main(String[] args) throws Exception {
        ColumnTableGenerator generator = new ColumnTableGenerator();

        Map<String, Pair<String, List<String>>> table = generator.generateTable(size1kb);
        generator.tableToTxt(table, "./src/test/java/org/example/data/size1kb.txt");

        table = generator.generateTable(size1mb);
        generator.tableToTxt(table, "./src/test/java/org/example/data/size1mb.txt");

        table = generator.generateTable(size1gb);
        generator.tableToTxt(table, "./src/test/java/org/example/data/size1gb.txt");

        table = generator.generateTable(size2gb);
        generator.tableToTxt(table, "./src/test/java/org/example/data/size2gb.txt");

        table = generator.generateTable(size3gb);
        generator.tableToTxt(table, "./src/test/java/org/example/data/size3gb.txt");
    }
}
