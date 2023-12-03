package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class ColumnEncoder<V> implements IEncoder<Pair<String, List<V>>> {
    private static final IEncoder<String> stringEncoder = new StringEncoder();
    private final IEncoder<List<V>> listEncoder = new ListEncoder<>();

    @Override
    public byte[] encode(Pair<String, List<V>> data) {
        List<byte[]> encodedDataList = new LinkedList<>();
        int bytesCounter = 0;

        byte[] encodedKey = stringEncoder.encode(data.getKey());

        encodedDataList.add(encodedKey);
        bytesCounter += encodedKey.length;

        byte[] encodedValue = listEncoder.encode(data.getValue());

        encodedDataList.add(encodedValue);
        bytesCounter += encodedValue.length;

        byte[] encodedData = new byte[bytesCounter];
        listToByteArray(encodedData, encodedDataList);

        return encodedData;
    }

    @Override
    public DecoderResult<Pair<String, List<V>>> decode(byte[] encodedData, int fromByte) {
        int offset = fromByte;

        DecoderResult<String> key = stringEncoder.decode(encodedData, offset);
        offset += key.getLength();

        DecoderResult<List<V>> value = listEncoder.decode(encodedData, offset);
        offset += value.getLength();

        Pair<String, List<V>> result = new ImmutablePair<>(key.getDecoderResult(), value.getDecoderResult());

        return new DecoderResult<>(result, offset - fromByte);
    }

    @Override
    public DecoderResult<Pair<String, List<V>>> decode(byte[] encodedData) {
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
