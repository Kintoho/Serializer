package Serialization.core;

public interface ISerializer<T> {
    public byte[] encode(T data);

    public DecoderResult<T> decode(byte[] bytes, int from);
}
