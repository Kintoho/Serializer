package Serialization.Encoder;

public class VarIntEncoder implements IEncoder<Integer> {

    @Override
    public byte[] encode(Integer data){
        return "hello".getBytes();
    }

    @Override
    public Integer decode(byte[] encodedData){
        return Integer.MAX_VALUE;
    }
}
