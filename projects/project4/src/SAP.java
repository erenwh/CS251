import java.util.NoSuchElementException;

/**
 * Created by HanWang on 3/29/17.
 */
public class SAP {
    private Digraph dg;

    // constructor
    public SAP(Digraph G) {
        this.dg = new Digraph(G);
    } // Use the Digraph implementation provided by the book

    // return length of shortest ancestral path of v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dg, w);
        int len = -1;
        int dist;
        for (int i = 0; i < dg.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                dist = bfsV.distTo(i) + bfsW.distTo(i);
                if (len < 0 || dist < len) {
                    len = dist;
                }
            }
        }
        return len;
    }

    // return a common ancestor of v and w that participates in a shortest
    // ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(dg, w);
        int len = -1;
        int dist;
        int ancestor = -1;
        for (int i = 0; i < dg.V(); i++) {
            if (bfs1.hasPathTo(i) && bfs2.hasPathTo(i)) {
                dist = bfs1.distTo(i) + bfs2.distTo(i);
                if (len < 0 || dist < len) {
                    len = dist;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    /**
     * takes the names of two files as command- line arguments: a digraph input file (standard format used by Digraph)
     * and a digraph test input file. Your main will construct the digraph and read in vertex pairs from the test input
     * file, and prints out the length of a shortest ancestral path between the two vertices and a common ancestor that
     * participates in that path. Note that there can be more than one path with the same length; output the
     * information corresponding to any of them.
     *
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        In test = new In(args[1]);

        Digraph d = new Digraph(in);
        SAP s = new SAP(d);


        while (!test.isEmpty()) {
            int v = test.readInt();
            int w = test.readInt();
            if (s.length(v,w) == -1) {
                System.out.println("sap = -1, ancestor = -1\n");
                return;
            }
            System.out.printf("sap = %d, ancestor = %d\n", s.length(v, w), s.ancestor(v, w));
        }
    }
}
