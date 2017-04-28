import java.util.Arrays;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString(); //Get a byte from stdIn
        BinaryStdIn.close();

        CircularSuffixArray bw = new CircularSuffixArray(s); //implement csa class

        for (int i = 0; i < s.length(); i++) {  // write row #
            if (bw.index(i) == 0) {
                BinaryStdOut.write(i);
            }
        }
        for (int i = 0; i < s.length(); i++) {  // write suffixes
            BinaryStdOut.write(getChar(s, bw.index(i)));
        }

        BinaryStdOut.close();
    }

    private static char getChar(String s, int index) {
        return s.charAt((index + s.length() - 1) % s.length());
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        /*int index = BinaryStdIn.readInt(); // get row #
        String input = BinaryStdIn.readString();  // get suffix string
        BinaryStdIn.close();

        String[] stringArr = new String[input.length()]; // construct string array

        //If sorted row i and j both start with the same character and i < j, then next[i] < next[j].
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                if (stringArr[j] == null) {
                    stringArr[j] = "";
                }
                stringArr[j] = input.charAt(j) + stringArr[j]; // concat
            }
            Arrays.sort(stringArr);
        }
        BinaryStdOut.write(stringArr[index]);
        BinaryStdOut.close();*/   // TOO SLOW!!!!

        int first = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();
        int[] count = new int[256 + 1];
        int[] next = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            ++count[input.charAt(i) + 1];
        }

        for (int i = 0; i < 256; i++) {
            count[i + 1] += count[i];
        }

        for (int i = 0; i < input.length(); i++) {
            next[count[input.charAt(i)]++] = i;
        }

        int nxt = next[first];

        BinaryStdOut.write(input.charAt(nxt));

        for (int i = 1; i < input.length(); i++) {
            nxt = next[nxt];
            BinaryStdOut.write(input.charAt(nxt));
        }

        BinaryStdOut.close();


    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        //Check args length
        if (args.length == 0) {
            StdOut.println("arg length is zero");
            return;
        }
        //Check for +/-
        switch (args[0]) {
            case "-":
                BurrowsWheeler.encode();
                break;
            case "+":
                BurrowsWheeler.decode();
                break;
            default:
                StdOut.println("arg length is zero");
                break;
        }
    }
}