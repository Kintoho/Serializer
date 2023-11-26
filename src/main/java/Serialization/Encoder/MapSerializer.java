package Serialization.Encoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Serialization.Encoder.Core.ISerializer;
import Serialization.Encoder.IntSerializer;

public class MapSerializer {
    private static final ISerializer<Long> longCoder = new IntSerializer();
    private static final ISerializer<String> strCoder = new StringSerializer();

    public byte[] encode(Map<String, String> data) {
        List<byte[]> encodedData = new LinkedList<>();
        for (String key : data.keySet()) {
            encodedData.add(strCoder.encode(key));
            encodedData.add(strCoder.encode(data.get(key)));
            System.out.println("ID = " + key + ", День недели = " + data.get(key));
        }
        System.out.println();
        return encodedData;
    }

    public byte[] encode(Map<String, List<Integer>> column) {
        byte[] encodedData = new byte[10];
        for (String key : column.keySet()) {
            encodedData = strCoder.encode(key);
            System.out.println("ID = " + key + ", День недели = " + column.get(key));
        }
        System.out.println();
        return encodedData;
    }
}
