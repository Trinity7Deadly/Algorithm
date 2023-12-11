package homework;

import edu.princeton.cs.algs4.*;

public class Deg {
    public static void main(String[] unused) {
        In file = new In("deg.txt") ;
        int v = file.readInt() ;
        int e = file.readInt() ;
        Graph g = new Graph(v + 1) ;

        for (int i = 0; i < e; i++) {
            int v1 = file.readInt() ;
            int v2 = file.readInt() ;
            g.addEdge(v1, v2) ;
        }

        for (int i = 1; i <= v; i++) {
            StdOut.print(g.degree(i) + " ") ;
        }
        StdOut.println() ;
    }
}
