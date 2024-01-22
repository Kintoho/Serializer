package deflate;

import java.io.IOException;
import java.util.*;

public class LZ77Data {
    private BitSet match = new BitSet();
    private StringBuilder dest = new StringBuilder();
    private int size;

    public LZ77Data(BitSet match, StringBuilder dest, int size) {
        this.match = match;
        this.dest = dest;
        this.size = size;
    }

    public BitSet getMatch() {
        return match;
    }

    public void setMatch(BitSet match) {
        this.match = match;
    }

    public StringBuilder getDest() {
        return dest;
    }

    public void setDest(StringBuilder dest) {
        this.dest = dest;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

