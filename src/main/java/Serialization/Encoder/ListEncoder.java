package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import java.util.LinkedList;
import java.util.List;

public class ListEncoder<V> implements IEncoder<List<V>> {
    private static final IEncoder<Integer> intEncoder = new IntEncoder();
    private IEncoder<V> encoder;

    @Override
    public byte[] encode(List<V> data) {
        List<byte[]> encodedDataList = new LinkedList<>();
        int bytesCounter = 0;

        if (data.get(0) instanceof String) {
            encoder = (IEncoder<V>) new StringEncoder();
        } else if (data.get(0) instanceof Integer) {
            encoder = (IEncoder<V>) new IntEncoder();
        } else if (data.get(0) instanceof Long) {
            encoder = (IEncoder<V>) new LongEncoder();
        } else if (data.get(0) instanceof Double) {
            encoder = (IEncoder<V>) new DoubleEncoder();
        } else {
            throw new IllegalStateException("Unexpected value: " + data.get(0).getClass());
        }

        // IEncoder<V> encoder = switch (data.get(0).getClass().getName()) {
        // case "String" -> new StringEncoder();
        // case "Integer" -> new IntEncoder();
        // case "Long" -> new LongEncoder();
        // case "Double" -> new DoubleEncoder();
        // default -> throw new IllegalStateException("Unexpected value: " +
        // data.get(0).getClass().getName());
        // };

        byte[] encodedListSize = intEncoder.encode(data.size());
        encodedDataList.add(encodedListSize);
        bytesCounter += encodedListSize.length;

        for (V datum : data) {
            byte[] encodedListItem = encoder.encode(datum);

            encodedDataList.add(encodedListItem);
            bytesCounter += encodedListItem.length;
        }

        byte[] encodedData = new byte[bytesCounter];
        listToByteArray(encodedData, encodedDataList);

        return encodedData;
    }

    @Override
    public DecoderResult<List<V>> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }

    @Override
    public DecoderResult<List<V>> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Integer> dataSize = intEncoder.decode(encodedData);
        int offset = fromByte + dataSize.getLength();
        List<V> result = new LinkedList<>();

        for (int i = 0; i < dataSize.getDecoderResult(); i++) {
            DecoderResult<V> listItem = encoder.decode(encodedData, offset);
            offset += listItem.getLength();

            result.add(listItem.getDecoderResult());
        }
        
        return new DecoderResult<List<V>>(result, offset - fromByte);
    }

    private void listToByteArray(byte[] result, List<byte[]> bytesList) {
        int pos = 0;

        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
