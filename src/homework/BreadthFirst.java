package homework;

import edu.princeton.cs.algs4.*;

public class BreadthFirst {
    public static void main(String[] unused) {
        In file = new In("Breadth.txt");
        int v = file.readInt();
        int e = file.readInt();
        Digraph g = new Digraph(v + 1);

        for (int i = 0; i < e; i++) {
            int v1 = file.readInt();
            int v2 = file.readInt();
            g.addEdge(v1, v2);
        }

        BreadthFirstDirectedPaths dist = new BreadthFirstDirectedPaths(g, 1) ;

        for (int i = 1; i <= v; i++) {
            if (dist.hasPathTo(i)) {
                StdOut.print(dist.distTo(i)) ;
            } else {
                StdOut.print("-1");
            }
            StdOut.print(" ");
        }
        StdOut.println() ;
    }
}