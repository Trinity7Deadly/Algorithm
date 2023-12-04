package homework;

import edu.princeton.cs.algs4.*;

public class CTE {
    public static void main(String[] unused) {
        In file = new In("CTE.txt");
        if (file.exists() && file.hasNextLine()) {
            int num = file.readInt();
            DirectedEdge edge1 = new DirectedEdge(0,0,0) ;

            for (int i = 1; i <= num ; i++) {
                int temp = 1 ;
                int v = file.readInt() ;
                int e = file.readInt() ;
                EdgeWeightedDigraph g = new EdgeWeightedDigraph(v + 1) ;
                EdgeWeightedDigraph g2 = new EdgeWeightedDigraph(v + 1) ;

                // Constructs the graph
                for (int k = 0; k < e; k++) {
                    int v1 = file.readInt() ;
                    int v2 = file.readInt() ;
                    int v3 = file.readInt() ;
                    g.addEdge(new DirectedEdge(v1, v2, v3)) ;
                    g2.addEdge(new DirectedEdge(v2, v1, v3)) ;

                    // Saves the first edge
                    if (temp == 1) {
                        edge1 = new DirectedEdge(v1, v2, v3) ;
                    }
                    temp++ ;
                }

                double cycle = 10000 ;

                /* Checks paths from vertices adjacent to endpoint of edge1
                   back to start point of edge1, records shortest to cycle. */
                for(DirectedEdge edge : g.adj(edge1.to())) {
                    DijkstraSP dist = new DijkstraSP(g, edge.from()) ;

                    if (dist.hasPathTo(edge1.from())) {
                        if (cycle > dist.distTo(edge1.from())) {
                            cycle = dist.distTo(edge1.from()) + edge1.weight() ;
                        }
                    }
                }

                // If cycle = starting value return -1, else return cycle
                if (cycle == 10000) {
                    StdOut.printf(-1 + " ") ;
                } else {
                    StdOut.printf((int)cycle + " ") ;
                }
            }
        }
    }
}