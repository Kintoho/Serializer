package Serialization.Encoder;

import Serialization.Encoder.Core.IEncoder;

import java.util.LinkedList;
import java.util.List;

public class ListEncoder <V> implements IEncoder<List<V>> {
    @Override
    public byte[] encode(List<V> data) {
        List<byte[]> encodedDataList = new LinkedList<>();
        int bytesCounter = 0;
        IEncoder encoder = switch (data.get(0).getClass().getName()) {
            case "String" -> new StringEncoder();
            case "Integer" -> new IntEncoder();
            default -> throw new IllegalStateException("Unexpected value: " + data.get(0).getClass().getName());
        };
        for (V datum : data) {
            encodedDataList.add(encoder.encode(datum));
            bytesCounter += encoder.encode(datum).length;
        }

        byte[] encodedData = new byte[bytesCounter];
        splitList(encodedData, encodedDataList);
        return encodedData;
    }

    @Override
    public List<V> decode(byte[] bytes) {
        return null;
    }

    private void splitList(byte[] result, List<byte[]> bytesList) {
        int pos = 0;
        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
