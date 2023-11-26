package Serialization.core;

public class DecoderResult<T> {
    private final T decoderResult;
    private final int length;

    public DecoderResult(T decoderResult, int length) {
        this.decoderResult = decoderResult;
        this.length = length;
    }

    public T getDecoderResult() {
        return decoderResult;
    }

    public int getLength() {
        return length;
    }
}