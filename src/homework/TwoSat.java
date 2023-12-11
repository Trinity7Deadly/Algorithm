package homework;

import edu.princeton.cs.algs4.*;

public class TwoSat {
    public static void main(String[] args) {
        In file = new In("2Sat.txt"); // Input file
        int numberOfFormulas = file.readInt();

        for (int i = 0; i < numberOfFormulas; i++) {
            int v = file.readInt(); // Number of variables
            int e = file.readInt(); // Number of clauses

            Digraph graph = buildGraph(v, e, file);
            GabowSCC scc = new GabowSCC(graph);

            if (Satisfiable(scc, v)) {
                System.out.print("1 "); // Satisfiable with assignment
                printAssignment(scc, v);
            } else {
                System.out.println("0"); // Unsatisfiable
            }
        }
    }

    private static Digraph buildGraph(int n, int m, In file) {
        Digraph graph = new Digraph(2 * n);

        for (int i = 0; i < m; i++) {
            int vOne = file.readInt();
            int vTwo = file.readInt();

            // Convert literals to indices in the graph
            int IndexV1;
            if (vOne < 0) { IndexV1 = -vOne - 1 + n; // If v1 is negative negate and subtract 1 then add n
            } else { IndexV1 = vOne - 1; } // If v is positive subtract 1

            int IndexV2;
            if (vTwo < 0) { IndexV2 = -vTwo - 1 + n; // If v2 is negative negate and subtract 1 then add n
            } else { IndexV2 = vTwo - 1; } // If v is positive subtract 1

            int NegationV1;
            if (vOne < 0) { NegationV1 = -vOne - 1; // If v1 is negative negate and subtract 1
            } else { NegationV1 = vOne - 1 + n; } // If v1 is positive subtract 1 then add n

            int NegationV2;
            if (vTwo < 0) { NegationV2 = -vTwo - 1; // If v2 is negative negate and subtract 1
            } else { NegationV2 = vTwo - 1 + n; } // If v2 is positive subtract 1 then add n

            // Add edges for implications
            graph.addEdge(NegationV1, IndexV2);
            graph.addEdge(NegationV2, IndexV1);
        }
        return graph;
    }

    private static boolean Satisfiable(GabowSCC scc, int n) {
        // Iterate through each variable
        for (int i = 0; i < n; i++) {
            // Check if the variable and its negation are in the same strongly connected component
            if (scc.stronglyConnected(i, i + n)) {
                return false; // If variable and its negation are in the same SCC, the formula is not satisfiable
            }
        }
        // If no conflicting pairs are found, the formula is satisfiable
        return true;
    }

    private static void printAssignment(GabowSCC scc, int n) {
        boolean[] truthAssignment = new boolean[2 * n];
        boolean[] assigned = new boolean[n];  // Track assigned variables

        // Determine truth assignment based on SCCs
        for (int i = 0; i < n; i++) {
            if (!assigned[i]) {
                int fake = i + n; // Represents the index of the negation of variable i
                int postLiteral = scc.id(i); // Postorder indices of literal
                int postNegate = scc.id(fake); // Postorder indices of negation

                // Check which comes first, the literal or its negation
                if (postLiteral < postNegate) {
                    // If the literal comes first, assign it true
                    truthAssignment[i] = true;
                } else {
                    // If the negation comes first, assign the literal false
                    truthAssignment[fake] = true;
                }
                // Mark assigned
                assigned[i] = true;
            }
        }
        // Print satisfying assignment
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
