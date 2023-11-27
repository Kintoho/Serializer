package Serialization.Encoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Serialization.Encoder.Core.IEncoder;

public class ColumnEncoder<V> {
    private static final IEncoder<Long> longCoder = new IntEncoder();
    private static final IEncoder<String> strCoder = new StringEncoder();
    private final IEncoder<List<V>> listCoder = new ListEncoder<>();

    public byte[] encode(Map<String, List<V>> column) {
        List<byte[]> encodedData = new LinkedList<>();
        for (String key : column.keySet()) {
            encodedData.add(strCoder.encode(key));
            encodedData.add(listCoder.encode(column.get(key)));
            System.out.println("ID = " + key + ", День недели = " + column.get(key));
        }
        System.out.println();

        byte[] result = new byte[1000]; // TODO:
        splitList(result, encodedData);
        return result;
    }

    private void splitList(byte[] result, List<byte[]> bytesList) {
        int pos = 0;
        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
