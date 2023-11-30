package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import java.util.*;

public class StringMapEncoder implements IEncoder<Map<String, String>> {

    private static final IEncoder<Integer> intEncoder = new IntEncoder();
    private static final IEncoder<String> stringEncoder = new StringEncoder();

    @Override
    public byte[] encode(Map<String, String> data) {
        List<byte[]> encodedDataList = new LinkedList<>();
        int bytesCounter = 0;

        Set<String> keys = data.keySet();
        byte[] encodedMapLength = intEncoder.encode(keys.size());

        encodedDataList.add(encodedMapLength);
        bytesCounter += encodedMapLength.length;

        for (String key : keys) {
            byte[] encodedKey = stringEncoder.encode(key);

            encodedDataList.add(encodedKey);
            bytesCounter += encodedKey.length;

            byte[] encodedValue = stringEncoder.encode(data.get(key));

            encodedDataList.add(encodedValue);
            bytesCounter += encodedValue.length;
        }

        byte[] encodedData = new byte[bytesCounter];
        splitList(encodedData, encodedDataList);
        
        return encodedData;
    }

    @Override
    public DecoderResult<Map<String, String>> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Integer> dataSize = intEncoder.decode(encodedData);
        int offset = fromByte + dataSize.getLength();
        Map<String, String> result = new HashMap<>(dataSize.getDecoderResult());

        for (int i = 0; i < dataSize.getDecoderResult(); i++) {
            DecoderResult<String> key = stringEncoder.decode(encodedData, offset);
            offset += key.getLength();

            DecoderResult<String> value = stringEncoder.decode(encodedData, offset);
            offset += value.getLength();
            
            result.put(key.getDecoderResult(), value.getDecoderResult());
        }
        return new DecoderResult<>(result, offset - fromByte);
    }

    @Override
    public DecoderResult<Map<String, String>> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }

    private void splitList(byte[] result, List<byte[]> bytesList) {
        int pos = 0;

        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
