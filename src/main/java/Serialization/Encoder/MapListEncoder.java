package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import java.util.*;

public class MapListEncoder<V> implements IEncoder<Map<String, List<V>>> {
    private static final IEncoder<String> stringEncoder = new StringEncoder();
    private final IEncoder<List<V>> listEncoder = new ListEncoder<>();

    @Override
    public byte[] encode(Map<String, List<V>> data) {
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

            byte[] encodedValue = listEncoder.encode(data.get(key));

            encodedDataList.add(encodedValue);
            bytesCounter += encodedValue.length;
        }

        byte[] encodedData = new byte[bytesCounter];
        listToByteArray(encodedData, encodedDataList);

        return encodedData;
    }

    @Override
    public DecoderResult<Map<String, List<V>>> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Long> dataSize = UIntEncoder.coder.decode(encodedData, fromByte);
        int offset = fromByte + dataSize.getLength();

        Map<String, List<V>> result = new HashMap<>(dataSize.getDecoderResult().intValue());

        for (int i = 0; i < dataSize.getDecoderResult(); i++) {
            DecoderResult<String> key = stringEncoder.decode(encodedData, offset);
            offset += key.getLength();

            DecoderResult<List<V>> value = listEncoder.decode(encodedData, offset);
            offset += value.getLength();

            result.put(key.getDecoderResult(), value.getDecoderResult());
        }
        return new DecoderResult<>(result, offset - fromByte);
    }

    @Override
    public DecoderResult<Map<String, List<V>>> decode(byte[] encodedData) {
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
