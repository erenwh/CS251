import java.util.*;

/**
 * Created by HanWang on 4/10/17.
 */
public class CircularSuffixArray {
    private final String str;
    private final List<Integer> indeces;
    private final int len;

    //version 2.0
    //modified circular suffix for memory saving. inplace

    public CircularSuffixArray(String str) {
        this.str = str;
        this.len = str.length();
        indeces = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            indeces.add(i);
        }
        Collections.sort(indeces, (o1, o2) -> {
            for (int i = 0; i < len; i++) {
                int com = Character.compare(str.charAt((o1+i)%len),str.charAt((o2+i)%len));
                if (com != 0) return com;
            }
            return 0;
        });
    }

    public int getLen() {
        return len;
    }

    public int index(int x) {
        return indeces.get(x);
    }
}