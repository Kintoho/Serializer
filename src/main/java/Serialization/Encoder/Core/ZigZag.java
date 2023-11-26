package Serialization.Encoder.Core;


public class ZigZag {
    public static Long wrap(long signedValue) {
        return (signedValue << 1) ^ (signedValue >> (64 - 1));
    }

    public static Long unwrap(long unsignedValue) {
        return (unsignedValue >> 1) ^ -(unsignedValue & 0x1);
    }

}
