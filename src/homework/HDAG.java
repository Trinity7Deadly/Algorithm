package homework;

import edu.princeton.cs.algs4.*;

// Hamiltonian Path in DAG
public class HDAG {
    public static void main(String[] unused) {
        In file = new In("HDAG.txt") ;
        int v = file.readInt() ;
        int e = file.readInt() ;
        Digraph g = new Digraph(v + 1) ;

        // Adds edges to graph
        for (int i = 0; i < e; i++) {
            int v1 = file.readInt() ;
            int v2 = file.readInt() ;
            g.addEdge(v1, v2) ;
        }

        Topological sort = new Topological(g) ;
        if (sort.hasOrder()) {
            for (int vertex : sort.order()) {
                if (vertex != 0) { // So that it doesn't print a 0 at the end
                    StdOut.printf(vertex + " ");
                }
            }
        }
    }
}