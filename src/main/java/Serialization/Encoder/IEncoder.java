package Serialization.Encoder;

public interface IEncoder<T> {

    byte[] encode(T data);

    T decode(byte[] encodedData);

}
