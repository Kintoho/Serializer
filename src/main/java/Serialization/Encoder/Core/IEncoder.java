package Serialization.Encoder.Core;

// import Serialization.Encoder.DecoderResult;

public interface IEncoder<T> {
    public byte[] encode(T data);

    public T decode(byte[] bytes);
}
