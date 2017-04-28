public class MoveToFront {
	private static int R = 256; // ASCII length

	private static char[] init() {
		char[] charArr = new char[R+1];
		for (char c = 0; c < R; c++) {
			charArr[c] = c;
		}
		return charArr;
	}

    private static void myExch(char[] charArr, int i, int j) {
        char tmp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = tmp;
    }

	// apply move-to-front encoding, reading from standard input and writing to standard output
	public static void encode() {
        //Initialize the array
        char[] charArr = init();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            charArr[R] = c;
            char i;
            for (i = 0; charArr[i] != c; i++) {
                myExch(charArr, i, R);
            }
            myExch(charArr, i, R);
            BinaryStdOut.write(i); //write char
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }
	// apply move-to-front decoding, reading from standard input and writing to standard output
	public static void decode()
	{
		//Initialize the array
		char[] charArr = init();

		while(!BinaryStdIn.isEmpty()) {
			char temp = BinaryStdIn.readChar();
			char c = charArr[temp];
			BinaryStdOut.write(c); //Print c
			//Move character at index to the front of sequence
            charArr[R] = c;
            char i;
            for (i = 0; charArr[i] != c; i++) {
                myExch(charArr, i, R);
            }
            myExch(charArr, i, R);
		}
		BinaryStdIn.close();
		BinaryStdOut.close();
	}
	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args)
	{
        //Check args length
        if (args.length == 0) {
            StdOut.println("arg length is zero");
            return;
        }

        //Check for +/-
        switch (args[0]) {
            case "-":
                MoveToFront.encode();
                break;
            case "+":
                MoveToFront.decode();
                break;
            default:
                StdOut.println("arg length is zero");
                break;
        }
	}
}