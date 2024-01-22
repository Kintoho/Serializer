import deflate.LZ77Data;

public final class Gunzip {

    public static void decompress(LZ77Data src, StringBuilder out) {
        int index = 0;
        int n = src.getSize();
        for (int i = 0; i < n; i++) {
            if (src.getMatch().get(i)) {
                int start = src.getDest().charAt(index++);
                int matchedLen = src.getDest().charAt(index++);
                int s = out.length() - start;
                int e = s + matchedLen;
                for (; s < e; s++) {
                    out.append(out.charAt(s));
                }
            } else {
                out.append(src.getDest().charAt(index++));
            }
        }
    }
}