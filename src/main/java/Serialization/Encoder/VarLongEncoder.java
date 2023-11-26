package Serialization.Encoder;

public class VarLongEncoder implements IEncoder<Long> {
    @Override
    public byte[] encode(Long data) {
        return new byte[0];
    }

    @Override
    public Long decode(byte[] encodedData) {
        return null;
    }
}
