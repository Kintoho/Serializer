import deflate.LZ77Data;
import java.io.*;
import java.util.*;

public class Gzip {
    public static void main(String[] args) throws Exception {
        String src = read("src/main/java/text.txt");
        int windowSize = 8192;
        LZ77Data ret = compress(src, windowSize);

        StringBuilder b = new StringBuilder();
        Gunzip.decompress(ret, b);
        StringBuilder dest = ret.getDest();

        int sz = dest.length();
        int bsz = ret.getSize() / 8 + (((ret.getSize()) % 8 == 0) ? 0 : 1);
        boolean eq = src.contentEquals(b);
        System.out.printf(
                "src: %d, comp: %d(%02.1f%%) + %dbytes, decomp: %d, %b%n",
                src.length(), sz, 1.0 * sz / src.length() * 100, bsz, b.length(), eq);
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) != b.charAt(i)) {
                System.out.println(String.format(
                        "%dth char different [%c:%c]",
                        i, src.charAt(i), b.charAt(i)));
                int s = Math.max(i - 5, 0);
                int e = Math.min(i + 5, src.length());
                System.out.println("src: " + src.substring(s, e));
                System.out.println("dec: " + b.substring(s, e));
                break;
            }
        }
    }

    public static LZ77Data compress(CharSequence src, int windowSize) throws IOException {
        BitSet match = new BitSet();
        StringBuilder out = new StringBuilder();
        int size = 0;
        Map<Character, List<Integer>> startPoss = new HashMap<Character, List<Integer>>();
        int n = src.length();
        for (int i = 0; i < n; i++) {
            char target = src.charAt(i);
            // find longest match
            boolean found = false;
            int start = 0;
            int matchLen = 0;
            List<Integer> poss = startPoss.get(target);
            if (poss != null) {
                Iterator<Integer> it = poss.iterator();
                while (it.hasNext()) {
                    int s = it.next();
                    if ((i - s) > windowSize) {
                        it.remove();
                        continue;
                    }
                    int len = getMatchedLen(src, s + 1, i + 1, n) + 1;
                    if (len > matchLen) {
                        start = i - s;
                        matchLen = len;
                    }
                    found = true;
                }
                poss.add(i);
                int jn = Math.min(i + matchLen, n);
                for (int j = i + 1; j < jn; j++) {
                    List<Integer> p = startPoss.get(src.charAt(j));
                    if (p == null) {
                        p = new LinkedList<Integer>();
                        startPoss.put(src.charAt(j), p);
                    }
                    p.add(j);
                }
            } else {
                poss = new LinkedList<Integer>();
                poss.add(i);
                startPoss.put(target, poss);
            }
            if (found && matchLen > 1) {
                match.set(size);
                out.append((char) start)
                        .append((char) matchLen);
                i += matchLen - 1;
            } else {
                match.set(size, false);
                out.append(target);
            }
            size++;
        }
        return new LZ77Data(match, out, size);
    }



    private static String read(String filename)
            throws IOException {
        InputStream is = new FileInputStream(filename);
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            char[] buff = new char[is.available() / 2];
            int i = 0;
            while (dis.available() > 0) {
                buff[i++] = dis.readChar();
            }
            return new String(buff);
        } finally {
            is.close();
        }
    }

    private static int getMatchedLen(CharSequence src, int i1, int i2, int end) {
        int n = Math.min(i2 - i1, end - i2);
        for (int i = 0; i < n; i++) {
            if (src.charAt(i1++) != src.charAt(i2++)) return i;
        }
        return 0;
    }
}