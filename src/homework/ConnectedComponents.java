package homework;

import edu.princeton.cs.algs4.* ;

public class ConnectedComponents {
    public static void main(String[] unused) {
        In file = new In("CC.txt") ;
        int v = file.readInt() ;
        int e = file.readInt() ;
        Graph g = new Graph(v + 1) ;

        // Adds edges to graph
        for (int i = 0; i < e; i++) {
            int v1 = file.readInt() ;
            int v2 = file.readInt() ;
            g.addEdge(v1, v2) ;
        }

        // Finds total -1, for empty, then prints
        CC total = new CC(g) ;
        StdOut.printf((total.count() - 1) + " ") ;
    }
}