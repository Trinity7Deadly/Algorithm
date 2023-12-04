package homework;

import edu.princeton.cs.algs4.* ;

public class Acyclicity {
    public static void main(String[] unused) {
        In file = new In("Acyclicity.txt") ;
        int num = file.readInt() ;

        // Creates graph
        for (int i = 0; i < num; i++) {
            int v = file.readInt() ;
            int e = file.readInt() ;
            Digraph g = new Digraph(v + 1) ;

            // Adds edges to graph
            for (int j = 0; j < e; j++) {
                int v1 = file.readInt() ;
                int v2 = file.readInt() ;
                g.addEdge(v1, v2) ;
            }

            DirectedCycle bicycle = new DirectedCycle(g) ;
            if(bicycle.hasCycle()) {
                StdOut.print("-1") ;
            } else { StdOut.print("1") ; }
            StdOut.print(" ") ;
        }
    }
}