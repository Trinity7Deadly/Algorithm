package homework;

import edu.princeton.cs.algs4.*;

public class StrongConComp {
    public static void main(String[] unused) {
        In file = new In("SCC.txt") ;
        int v = file.readInt() ;
        int e = file.readInt() ;
        Digraph g = new Digraph(v + 1) ;

        // Adds edges to graph
        for (int i = 0; i < e; i++) {
            int v1 = file.readInt() ;
            int v2 = file.readInt() ;
            g.addEdge(v1, v2) ;
        }

        // Finds total -1, for empty, then prints
        GabowSCC total = new GabowSCC(g) ;
        StdOut.printf((total.count() - 1) + " ") ;
    }
}