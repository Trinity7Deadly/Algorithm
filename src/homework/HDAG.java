package homework;

import edu.princeton.cs.algs4.*;

// Hamiltonian Path in DAG
public class HDAG {
    public static void main(String[] unused) {
        In file = new In("HDAG.txt") ;
        if (file.exists() && file.hasNextLine()) {
            int num = file.readInt() ;

            for (int i = 1; i <= num; i++) {
                int v = file.readInt() ;
                int e = file.readInt() ;
                Digraph g = new Digraph(v + 1) ;

                // Adds edges to graph
                for (int k = 0; k < e; k++) {
                    int v1 = file.readInt() ;
                    int v2 = file.readInt() ;
                    g.addEdge(v1, v2) ;
                }

                Topological sort = new Topological(g) ;
                String paper = null ;
                boolean exit = false ;

                // Checks if it can be sorted
                if (sort.hasOrder()) {
                    paper = "1 " ;
                    int preRelease = 0 ;

                    for (int vertex : sort.order()) {
                        if (vertex != 0) { // So that it doesn't print a 0 at the end
                            if (preRelease == 0) {
                                preRelease = vertex ;
                                paper += vertex + " " ;
                            } else {
                                exit = true ;

                                for (int temp : g.adj(preRelease)) {
                                    if (vertex == temp) {
                                        paper += vertex + " " ;
                                        preRelease = vertex ;
                                        exit = false ;
                                    }
                                }
                                if (exit) { paper = "-1" ; break ; }
                            }
                        } else break ;
                    }
                    StdOut.println(paper + "\n") ;
                }
            }
        }
    }
}