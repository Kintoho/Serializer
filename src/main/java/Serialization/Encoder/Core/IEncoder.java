package Serialization.Encoder.Core;

public interface IEncoder<T> {
    byte[] encode(T data);

    DecoderResult<T> decode(byte[] encodedData);
    DecoderResult<T> decode(byte[] encodedData, int fromByte);
}
