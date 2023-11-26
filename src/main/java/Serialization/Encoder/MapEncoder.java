package Serialization.Encoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Serialization.Encoder.Core.IEncoder;

public class MapEncoder {
    private static final IEncoder<Long> longCoder = new IntEncoder();
    private static final IEncoder<String> strCoder = new StringEncoder();

    public byte[] encode(Map<String, String> data) {
        List<byte[]> encodedData = new LinkedList<>();
        for (String key : data.keySet()) {
            encodedData.add(strCoder.encode(key));
            encodedData.add(strCoder.encode(data.get(key)));
            System.out.println("ID = " + key + ", День недели = " + data.get(key));
        }
        System.out.println();

        byte[] result = new byte[1000]; // TODO:
        splitList(result, encodedData);
        return result;
    }

    public byte[] encode(Map<String, List> column) {
        byte[] encodedData = new byte[10];
        for (String key : column.keySet()) {
            encodedData = strCoder.encode(key);
            System.out.println("ID = " + key + ", День недели = " + column.get(key));
        }
        System.out.println();
        return encodedData;
    }

    private void splitList(byte[] result, List<byte[]> bytesList) {
        int pos = 0;
        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
