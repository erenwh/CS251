
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


/**
 * @Author Han Wang
 * @Date 3/29/17
 */

public final class WordNet {
    private Hashtable<String, String> nounsMap = new Hashtable<>();
    private Hashtable<Integer, String> synsetMap = new Hashtable<>();
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new NullPointerException();

        // get synsets
        In synIn = new In(synsets);
        int counter = 1;

        //45,AND_circuit AND_gate,a circuit in a computer that fires only when all of its inputs fire
        while (!synIn.isEmpty()) {
            counter++;
            String line = synIn.readLine();
            String[] ss = line.split(",");
            Integer id = Integer.parseInt(ss[0]); // 45
            String[] nouns = ss[1].split(" ");


            for (int i = 0; i < nouns.length; i++) {
                if (nounsMap.get(nouns[i]) == null)
                    nounsMap.put(nouns[i], id.toString());
                else
                    nounsMap.put(nouns[i], nounsMap.get(nouns[i]) + "," + id.toString());
            }
            synsetMap.put(id, ss[1]);

        }

        Digraph graph = new Digraph(counter);


        // get hypernyms
        In hypIn = new In(hypernyms);
        //171,22798,57458
        while (!hypIn.isEmpty()) {
            String line = hypIn.readLine();
            String[] ss = line.split(",");
            int id = Integer.parseInt(ss[0]); // 171

            for (int i = 1; i < ss.length; ++i) {
                int idHyp = Integer.parseInt(ss[i]);
                graph.addEdge(id, idHyp);
            }

        }


        sap = new SAP(graph);

    }

    // is the word a WordNet noun? This can be used to search for existing
    // nouns at the beginning of the printSap method
    public boolean isNoun(String word) {
        if (word == null)
            throw new NullPointerException("isNoun\n");
        return (String)nounsMap.get(word) != null;
    }

    // string -> set of integer
    // print the synset (second field of synsets.txt) that is the common ancestor
    // of nounA and nounB in a shortest ancestral path as well as the length of the path,
    // following this format: "sap<space>=<space><number>,<space>ancestor<space>=<space><synsettext>"
    // If no such path exists the sap should contain -1 and ancestor should say "null"
    // This method should use the previously defined SAP datatype
    public void printSap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new java.lang.NullPointerException();

        if (!isNoun(nounA) || !isNoun(nounB)){
            StdOut.println("sap = -1, ancestor = null");
            return;
        }

        String aString = nounsMap.get(nounA);
        String bString = nounsMap.get(nounB);

        String[] a = aString.split(",");
        String[] b = bString.split(",");

        int v, w, length = 0, ancestorID = 0;
        boolean marked = false;

        for (String as : a) {
            v = Integer.parseInt(as);
            for (String bs : b) {
                w = Integer.parseInt(bs);
                int tempLength = sap.length(v, w);
                if (!marked) {
                    length = tempLength;
                    marked = true;
                    ancestorID = sap.ancestor(v, w);
                } else {
                    if (tempLength < length) {
                        length = tempLength;
                        ancestorID = sap.ancestor(v, w);
                    }
                }
            }
        }

        String ancestor = synsetMap.get(ancestorID);

        StdOut.println("sap = " + length + ", ancestor = " + ancestor);

    }


    public static void main(String[] args) {
        WordNet wn = new WordNet(args[0], args[1]);
        In input = new In(args[2]);

        while (!input.isEmpty()) {
            String line = input.readLine();
            String[] nouns = line.split(" ");
            wn.printSap(nouns[0], nouns[1]);
        }
    }
}