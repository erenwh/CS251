Author: Han Wang

No known error found.

the stdout from Sap tend to have a new line after the correct output; and WordNet tend not to have it. Do not know the strict rule on this, but the outputs are correct. The new line format matching problem is minor.

Compile using:
javac -classpath .:stdlib.jar Bag.java
javac -classpath .:stdlib.jar Stack.java
javac -classpath .:stdlib.jar Queue.java
javac -classpath .:stdlib.jar BreadthFirstDirectedPaths.java
javac -Xlint:-unchecked -classpath .:stdlib.jar Digraph.java
javac -Xlint:-unchecked -classpath .:stdlib.jar Graph.java
javac -classpath .:stdlib.jar DepthFirstPaths.java
javac -classpath .:stdlib.jar SAP.java
javac -classpath .:stdlib.jar WordNet.java

Run using:
java -classpath .:stdlib.jar SAP digraph1.txt digraph1.input
java -classpath .:stdlib.jar WordNet synsets.txt hypernyms.txt wordnet.input