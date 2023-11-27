package Serialization.Encoder;

import Serialization.Encoder.Core.IEncoder;

import java.util.List;

public class ListEncoder <V> implements IEncoder<List<V>> {
    @Override
    public byte[] encode(List<V> data) {
        return new byte[0];
    }

    @Override
    public List<V> decode(byte[] bytes) {
        return null;
    }
}
