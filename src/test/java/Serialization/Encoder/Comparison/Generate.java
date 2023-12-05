package Serialization.Encoder.Comparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.tuple.Pair;

import com.google.protobuf.ListValue;
import com.google.protobuf.Value;

public class Generate {
    public static Pair<Map<String, List<Integer>>, Map<String, ListValue>> generateInteger(int rows, int cols) {
        List<Integer> list = new ArrayList<>(rows);
        ListValue.Builder protoListBuilder = ListValue.newBuilder();

        for (int i = 1; i <= rows; i++) {
            int randomInt = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE / 2);
            list.add(randomInt);
            protoListBuilder.addValues(Value.newBuilder().setNumberValue(randomInt).build());
        }

        Map<String, List<Integer>> Map = new HashMap<>();
        Map<String, ListValue> protoMap = new HashMap<>();

        for (int i = 1; i <= cols; i++) {
            byte[] bytes = new byte[100];
            ThreadLocalRandom.current().nextBytes(bytes);
            String key = bytes.toString();

            Map.put(key+i, list);
            protoMap.put(key+i, protoListBuilder.build());
        }

        return Pair.of(Map, protoMap);
    }

    public static Pair<Map<String, List<Long>>, Map<String, ListValue>> generateLong(int rows, int cols) {
        List<Long> list = new ArrayList<>(rows);
        ListValue.Builder protoListBuilder = ListValue.newBuilder();

        for (int i = 1; i <= rows; i++) {
            long randomLong = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE / 2);
            list.add(randomLong);
            protoListBuilder.addValues(Value.newBuilder().setNumberValue(randomLong).build());
        }

        Map<String, List<Long>> Map = new HashMap<>();
        Map<String, ListValue> protoMap = new HashMap<>();

        for (int i = 1; i <= cols; i++) {
            byte[] bytes = new byte[100];
            ThreadLocalRandom.current().nextBytes(bytes);
            String key = bytes.toString();

            Map.put(key+i, list);
            protoMap.put(key+i, protoListBuilder.build());
        }

        return Pair.of(Map, protoMap);
    }

    public static Pair<Map<String, List<Double>>, Map<String, ListValue>> generateDouble(int rows, int cols) {
        List<Double> list = new ArrayList<>(rows);
        ListValue.Builder protoListBuilder = ListValue.newBuilder();

        for (int i = 1; i <= rows; i++) {
            Double randomDouble = ThreadLocalRandom.current().nextDouble(Double.MAX_VALUE / 2);
            list.add(randomDouble);
            protoListBuilder.addValues(Value.newBuilder().setNumberValue(randomDouble).build());
        }

        Map<String, List<Double>> Map = new HashMap<>();
        Map<String, ListValue> protoMap = new HashMap<>();

        for (int i = 1; i <= cols; i++) {
            byte[] bytes = new byte[100];
            ThreadLocalRandom.current().nextBytes(bytes);
            String key = bytes.toString();

            Map.put(key+i, list);
            protoMap.put(key+i, protoListBuilder.build());
        }

        return Pair.of(Map, protoMap);
    }

    public static Pair<Map<String, List<String>>, Map<String, ListValue>> generateString(int rows, int cols) {
        List<String> list = new ArrayList<>(rows);
        ListValue.Builder protoListBuilder = ListValue.newBuilder();

        for (int i = 1; i <= rows; i++) {
            byte[] bytes = new byte[100];
            ThreadLocalRandom.current().nextBytes(bytes);
            String randomString = bytes.toString();

            list.add(randomString);
            protoListBuilder.addValues(Value.newBuilder().setStringValue(randomString).build());
        }

        Map<String, List<String>> Map = new HashMap<>();
        Map<String, ListValue> protoMap = new HashMap<>();

        for (int i = 1; i <= cols; i++) {
            byte[] bytes = new byte[100];
            ThreadLocalRandom.current().nextBytes(bytes);
            String key = bytes.toString();

            Map.put(key+i, list);
            protoMap.put(key+i, protoListBuilder.build());
        }

        return Pair.of(Map, protoMap);
    }
}
