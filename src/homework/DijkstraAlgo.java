package homework;

import edu.princeton.cs.algs4.*;

public class DijkstraAlgo {
    public static void main(String[] unused) {
        In file = new In("Dijkstra.txt");
        int v = file.readInt();
        int e = file.readInt();
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(v + 1);

        for (int i = 0; i < e; i++) {
            int v1 = file.readInt();
            int v2 = file.readInt();
            int v3 = file.readInt();
            g.addEdge(new DirectedEdge(v1, v2, v3));
        }

        DijkstraSP dist = new DijkstraSP(g, 1) ;

        for (int i = 1; i <= v; i++) {
            if (dist.hasPathTo(i)) {
                StdOut.print((int)dist.distTo(i)) ;
            } else {
                StdOut.print("-1");
            }
            StdOut.print(" ");
        }
        StdOut.println() ;
    }
}