package homework;

import edu.princeton.cs.algs4.*;
import java.util.Scanner;

public class TwoSat2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfFormulas = scanner.nextInt();

        for (int i = 0; i < numberOfFormulas; i++) {
            int n = scanner.nextInt(); // Number of variables
            int m = scanner.nextInt(); // Number of clauses

            Digraph graph = constructGraph(n, m, scanner);

            KosarajuSharirSCC scc = new KosarajuSharirSCC(graph);

            if (isSatisfiable(scc, n)) {
                System.out.print("1 ");
                printSatisfyingAssignment(scc, n);
            } else {
                System.out.println("0");
            }
        }

        scanner.close();
    }

    private static Digraph constructGraph(int n, int m, Scanner scanner) {
        Digraph graph = new Digraph(2 * n);

        for (int i = 0; i < m; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            // Convert literals to indices in the graph
            int vIndex = (v < 0 ? -v - 1 + n : v - 1);
            int wIndex = (w < 0 ? -w - 1 + n : w - 1);
            int notVIndex = (v < 0 ? -v - 1 : v - 1 + n);
            int notWIndex = (w < 0 ? -w - 1 : w - 1 + n);

            // Add edges for implications
            graph.addEdge(notVIndex, wIndex);
            graph.addEdge(notWIndex, vIndex);
        }

        return graph;
    }

    private static boolean isSatisfiable(KosarajuSharirSCC scc, int n) {
        for (int i = 0; i < n; i++) {
            if (scc.stronglyConnected(i, i + n)) {
                return false; // If variable and its negation are in the same SCC
            }
        }
        return true;
    }

    private static void printSatisfyingAssignment(KosarajuSharirSCC scc, int n) {
        boolean[] truthAssignment = new boolean[2 * n];
        boolean[] assigned = new boolean[n];  // Keep track of whether a variable has been assigned

        // Determine the truth assignment based on SCCs
        for (int i = 0; i < n; i++) {
            if (!assigned[i]) {
                int falseIndex = i + n;

                // Check which of the literal or its negation comes first in the postorder
                if (scc.id(i) < scc.id(falseIndex)) {
                    // If the literal comes first, assign it true
                    truthAssignment[i] = true;
                } else {
                    // If the negation comes first, assign the literal false
                    truthAssignment[falseIndex] = true;
                }
                assigned[i] = true;
            }
        }

        // Print the satisfying assignment
        for (int i = 0; i < n; i++) {
            if (truthAssignment[i]) {
                System.out.print((i + 1) + " ");
            } else if (truthAssignment[i + n]) {
                System.out.print(-(i + 1) + " ");
            }
        }

        System.out.println();
    }

}
