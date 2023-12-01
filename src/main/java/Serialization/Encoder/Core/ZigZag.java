package Serialization.Encoder.Core;

public class ZigZag {
    public static long wrap(long signedValue) {
        return (signedValue << 1) ^ (signedValue >> (64 - 1));
    }

    public static long unwrap(long unsignedValue) {
        return (unsignedValue >> 1) ^ -(unsignedValue & 0x1);
    }

}
