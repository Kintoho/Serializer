package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class TableEncoder<V> implements IEncoder<Map<String, Pair<String, List<V>>>> {
    private static final IEncoder<String> stringEncoder = new StringEncoder();
    private final IEncoder<Pair<String, List<V>>> columnEncoder = new ColumnEncoder<>();

    @Override
    public byte[] encode(Map<String, Pair<String, List<V>>> data) {
        List<byte[]> encodedDataList = new LinkedList<>();
        int bytesCounter = 0;

        Set<String> keys = data.keySet();
        byte[] encodedMapLength = UIntEncoder.coder.encode((long) keys.size());

        encodedDataList.add(encodedMapLength);
        bytesCounter += encodedMapLength.length;

        for (String key : keys) {
            byte[] encodedKey = stringEncoder.encode(key);

            encodedDataList.add(encodedKey);
            bytesCounter += encodedKey.length;

            byte[] encodedValue = columnEncoder.encode(data.get(key));

            encodedDataList.add(encodedValue);
            bytesCounter += encodedValue.length;
        }

        byte[] encodedData = new byte[bytesCounter];
        listToByteArray(encodedData, encodedDataList);

        return encodedData;
    }

    @Override
    public DecoderResult<Map<String, Pair<String, List<V>>>> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Long> dataSize = UIntEncoder.coder.decode(encodedData, fromByte);
        int offset = fromByte + dataSize.getLength();

        Map<String, Pair<String, List<V>>> result = new HashMap<>(dataSize.getDecoderResult().intValue());

        for (int i = 0; i < dataSize.getDecoderResult(); i++) {
            DecoderResult<String> key = stringEncoder.decode(encodedData, offset);
            offset += key.getLength();

            DecoderResult<Pair<String, List<V>>> value = columnEncoder.decode(encodedData, offset);
            offset += value.getLength();

            result.put(key.getDecoderResult(), value.getDecoderResult());
        }
        return new DecoderResult<>(result, offset - fromByte);
    }

    @Override
    public DecoderResult<Map<String, Pair<String, List<V>>>> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }

    private void listToByteArray(byte[] result, List<byte[]> bytesList) {
        int pos = 0;

        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
