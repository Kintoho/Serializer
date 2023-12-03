package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import java.util.ArrayList;
import java.util.List;

public class ListEncoder<V> implements IEncoder<List<V>> {
    private IEncoder<V> coder;

    @Override
    public byte[] encode(List<V> data) {
        List<byte[]> encodedDataList = new ArrayList<>();
        int bytesCounter = 0;

        if (data.get(0) instanceof String) {
            coder = (IEncoder<V>) new StringEncoder();
        } else if (data.get(0) instanceof Integer) {
            coder = (IEncoder<V>) new IntEncoder();
        } else if (data.get(0) instanceof Long) {
            coder = (IEncoder<V>) new LongEncoder();
        } else if (data.get(0) instanceof Double) {
            coder = (IEncoder<V>) new DoubleEncoder();
        } else if (data.get(0) instanceof List) {
            coder = (IEncoder<V>) new ListEncoder();
        } else {
            throw new IllegalStateException("Unexpected value: " + data.get(0).getClass());
        }

        byte[] encodedListSize = UIntEncoder.coder.encode((long) data.size());

        encodedDataList.add(encodedListSize);
        bytesCounter += encodedListSize.length;

        for (V datum : data) {
            byte[] encodedListItem = coder.encode(datum);

            encodedDataList.add(encodedListItem);
            bytesCounter += encodedListItem.length;
        }

        byte[] encodedData = new byte[bytesCounter];
        listToByteArray(encodedData, encodedDataList);

        return encodedData;
    }

    @Override
    public DecoderResult<List<V>> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Long> dataSize = UIntEncoder.coder.decode(encodedData, fromByte);
        int offset = fromByte + dataSize.getLength();

        List<V> result = new ArrayList<>();

        for (int i = 0; i < dataSize.getDecoderResult(); i++) {
            DecoderResult<V> listItem = coder.decode(encodedData, offset);
            offset += listItem.getLength();

            result.add(listItem.getDecoderResult());
        }

        return new DecoderResult<List<V>>(result, offset - fromByte);
    }

    @Override
    public DecoderResult<List<V>> decode(byte[] encodedData) {
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

// IEncoder<V> encoder = switch (data.get(0).getClass().getName()) {
// case "String" -> new StringEncoder();
// case "Integer" -> new IntEncoder();
// case "Long" -> new LongEncoder();
// case "Double" -> new DoubleEncoder();
// default -> throw new IllegalStateException("Unexpected value: " +
// data.get(0).getClass().getName());
// };