Author: Han Wang

Added class CircularSuffixarray.java for helper

No known error found.

**************** abra Test *****************
Compile using:
javac -cp .:stdlib.jar:algs4.jar BurrowsWheeler.java
javac -cp .:stdlib.jar:algs4.jar MoveToFront.java
javac -cp .:stdlib.jar HexDump.java
javac -cp .:stdlib.jar:algs4.jar Huffman.java

Encode:
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler - < abra.txt > abraTest.txt.bwt
java -cp .:stdlib.jar:algs4.jar MoveToFront - < abraTest.txt.bwt > abraTest.txt.bwt.mtf
java -cp .:stdlib.jar:algs4.jar Huffman - < abraTest.txt.bwt.mtf > abraTest.txt.bwt.mtf.huf

Decode:
java -cp .:stdlib.jar:algs4.jar Huffman + < abraTest.txt.bwt.mtf.huf > abraTestRe.txt.bwt.mtf
java -cp .:stdlib.jar:algs4.jar MoveToFront + < abraTestRe.txt.bwt.mtf > abraTestRe.txt.bwt
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler + < abraTestRe.txt.bwt > abraTestRe.txt


diff:
diff abraTestRe.txt abra.txt
********************************************

****************** aesop test *****************
Compile using:
javac -cp .:stdlib.jar:algs4.jar BurrowsWheeler.java
javac -cp .:stdlib.jar:algs4.jar MoveToFront.java
javac -cp .:stdlib.jar HexDump.java
javac -cp .:stdlib.jar:algs4.jar Huffman.java

Encode:
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler - < aesop.txt > aesopTest.txt.bwt
java -cp .:stdlib.jar:algs4.jar MoveToFront - < aesopTest.txt.bwt > aesopTest.txt.bwt.mtf
java -cp .:stdlib.jar:algs4.jar Huffman - < aesopTest.txt.bwt.mtf > aesopTest.txt.bwt.mtf.huf

Decode:
java -cp .:stdlib.jar:algs4.jar Huffman + < aesopTest.txt.bwt.mtf.huf > aesopTestRe.txt.bwt.mtf
java -cp .:stdlib.jar:algs4.jar MoveToFront + < aesopTestRe.txt.bwt.mtf > aesopTestRe.txt.bwt
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler + < aesopTestRe.txt.bwt > aesopTestRe.txt


diff:
diff aesopTestRe.txt aesop.txt
********************************************

****************** gif test *****************
Compile using:
javac -cp .:stdlib.jar:algs4.jar BurrowsWheeler.java
javac -cp .:stdlib.jar:algs4.jar MoveToFront.java
javac -cp .:stdlib.jar HexDump.java
javac -cp .:stdlib.jar:algs4.jar Huffman.java

generating gif file
java -cp .:stdlib.jar:algs4.jar Huffman + < us.gif.huf > us.gif

Encode:
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler - < us.gif > usTest.gif.bwt
java -cp .:stdlib.jar:algs4.jar MoveToFront - < usTest.gif.bwt > usTest.gif.bwt.mtf
java -cp .:stdlib.jar:algs4.jar Huffman - < usTest.gif.bwt.mtf > usTest.gif.bwt.mtf.huf

Decode:
java -cp .:stdlib.jar:algs4.jar Huffman + < usTest.gif.bwt.mtf.huf > usTestRe.gif.bwt.mtf
java -cp .:stdlib.jar:algs4.jar MoveToFront + < usTestRe.gif.bwt.mtf > usTestRe.gif.bwt
java -cp .:stdlib.jar:algs4.jar BurrowsWheeler + < usTestRe.gif.bwt > usTestRe.gif


diff:
diff usTestRe.gif us.gif
********************************************



*************Below is unit test command; no use for final test**************


run statement to send encoded to decoded
java -cp .:stdlib.jar BurrowsWheeler - < abra.txt | java -cp .:stdlib.jar:algs4.jar BurrowsWheeler +
 
run statement to send encoded to HexDump
java -cp .:stdlib.jar BurrowsWheeler - < abra.txt | java -cp .:stdlib.jar HexDump
 
run statement to create mtf file after encode (abra2 so you don't overwrite the file they gave)
java -cp .:stdlib.jar MoveToFront - < abra.txt > abra2.txt.mtf
run statement to create a new .txt file after decode (aesop2.txt so you don't overwrite the file they gave)
java .:stdlib.jar BurrowsWheeler + < aesop.txt.bwt > aesop2.txt

********************************************