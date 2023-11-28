package Serialization.Encoder.Core;

import Serialization.Encoder.DecoderResult;

public interface IEncoder<T> {
    public byte[] encode(T data);

    public DecoderResult<T> decode(byte[] bytes);
}
