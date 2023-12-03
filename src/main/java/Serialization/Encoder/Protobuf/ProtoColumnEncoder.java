package Serialization.Encoder.Protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ListValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtoColumnEncoder {

    private static final Map<String, List<Integer>> column = new HashMap<>();
    private static final List<Integer> values = new ArrayList<>();
    private static final ColumnOuterClass.Column columnOuterClass = ColumnOuterClass.Column.newBuilder().build();

    public static void main(String[] args) throws InvalidProtocolBufferException {
        values.add(1);
        values.add(2);

        column.put("First", values);
        long startTime = System.currentTimeMillis();
        byte[] encodedColumn = columnOuterClass.toByteArray();
        long endTime = System.currentTimeMillis();
        System.out.println("Time to encode: " + (endTime - startTime));
        System.out.println(encodedColumn);
        //ListValue decodedColumn = ColumnOuterClass.Column.parseFrom(encodedColumn).getDataOrThrow("First");
        System.out.println(ColumnOuterClass.Column.parseFrom(encodedColumn).getDataMap());
    }
}
