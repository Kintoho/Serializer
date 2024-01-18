package org.example.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class ColumnTableGenerator {

    public Map<String, Pair<String, List<String>>> generateTable(int size) {
        Map<String, Pair<String, List<String>>> result = new HashMap<>();

        int cols = 64;
        int rows = (size / cols - 12) / 4;

        for (int i = 0; i < cols; i++) {
            String key = RandomStringUtils.randomAlphabetic(6);
            List<String> list = new ArrayList<>(rows);

            for (int j = 0; j < rows; j++) {
                list.add(RandomStringUtils.randomAlphanumeric(4));
            }
            
            result.put(key, new ImmutablePair<>(key, list));
        }
        
        return result;
    }

    public void tableToTxt(Map<String, Pair<String, List<String>>> table, String path) {

        try{
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (Pair<String, List<String>> pair : table.values()) {
                List<String> row = pair.getRight();
                row.add(0, pair.getLeft());
                
                bw.write(String.join(",", row));
                bw.newLine();
            }          
            
            // Close connection
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
