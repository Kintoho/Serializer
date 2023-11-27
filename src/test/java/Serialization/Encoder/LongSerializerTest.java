package Serialization.Encoder;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LongSerializerTest {

    private static final int CONTINUE_BIT = 0x80;

    @Test
    public void decodingTest() {
        //List<Long> testData = List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L);
        List<Long> testData = List.of(10L, 56L, 112L);
        IntEncoder coder = new IntEncoder();

        for (long x : testData) {
            byte[] encodedBytes = coder.encode(x);
            Long decoded = coder.decode(encodedBytes);
            //assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.longValue());
        }
    }

    @Test
    public void testMinMaxValues() {
        List<Long> wrongData = List.of(Long.MAX_VALUE, Long.MAX_VALUE - 500, Long.MIN_VALUE, Long.MIN_VALUE + 500);
        IntEncoder coder = new IntEncoder();
        for (long x : wrongData) {
            assertThrows(RuntimeException.class, () -> coder.encode(x));
        }
    }
}