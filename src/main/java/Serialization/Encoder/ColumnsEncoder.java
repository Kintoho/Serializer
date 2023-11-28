package Serialization.Encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

public class ColumnsEncoder<V> {
    private static final IEncoder<Integer> intEncoder = new IntEncoder();
    private static final IEncoder<String> stringEncoder = new StringEncoder();
    private final IEncoder<List<V>> listEncoder = new ListEncoder<>();

    public byte[] encode(Map<String, List<V>> column) {
        List<byte[]> encodedDataList = new ArrayList<>();
        int bytesCounter = 0;
        for (String key : column.keySet()) {
            encodedDataList.add(stringEncoder.encode(key));
            bytesCounter += stringEncoder.encode(key).length;
            encodedDataList.add(listEncoder.encode(column.get(key)));
        }

        byte[] encodedColumn = new byte[bytesCounter];
        splitList(encodedColumn, encodedDataList);
        return encodedColumn;
    }

    public DecoderResult<Map<String, List<V>>> decode(byte[] encodedData, int fromByte) {

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
