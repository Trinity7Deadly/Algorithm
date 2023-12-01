package homework;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DoubleDeg {
    public static void main(String[] unused) {
        In file = new In("ddeg.txt") ;
        int v = file.readInt() ;
        int e = file.readInt() ;
        Graph g = new Graph(v + 1) ;

        for (int i = 0; i < e; i++) {
            int v1 = file.readInt() ;
            int v2 = file.readInt() ;
            g.addEdge(v1, v2) ;
        }

        int sum ;
        for (int i = 1; i <= v; i++) {
            sum = 0 ;
            for (int k : g.adj(i)) {
                sum = sum + g.degree(k) ;
            }
            StdOut.print(sum + " ") ;
        }
        StdOut.println() ;
    }
}
