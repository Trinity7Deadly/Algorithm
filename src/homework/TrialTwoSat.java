package homework;

import edu.princeton.cs.algs4.*;

public class TrialTwoSat {
    public static void main(String[] args) {
        In file = new In("2Sat.txt");
        int v = file.readInt();
        int e = file.readInt();

        int[][] tests = new int[e][2];
        for (int i = 0; i < e; i++) {
            tests[i][0] = file.readInt();
            tests[i][1] = file.readInt();
        }

        int[] result = solve2SAT(v, e, tests);

        if (result[0] == 0) {
            StdOut.println("0");  // Unsatisfiable
        } else {
            StdOut.println("1 " + arrayToString(result, 1, v));  // Satisfiable with assignment
        }
    }

    // Implement the solve2SAT method and other necessary functions here
    private static int[] solve2SAT(int n, int m, int[][] tests) {
        int vertices = 2 * n;
        Digraph implicationGraph = buildImplicationGraph(n, tests);
        GabowSCC scc = new GabowSCC(implicationGraph);

        // Check if a variable and its negation are in the same SCC
        for (int i = 0; i < n; i++) {
            if (scc.id(i) == scc.id(i + n)) {
                return new int[]{0}; // Unsatisfiable
            }
        }

        // Build the assignment based on SCC
        int[] assignment = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // Assign 1 if variable i is in a larger SCC, 0 otherwise
            assignment[i + 1] = scc.id(i) > scc.id(i + n) ? 1 : 0;
        }

        return assignment;
    }

    private static Digraph buildImplicationGraph(int n, int[][] tests) {
        int vertices = 2 * n;
        Digraph implicationGraph = new Digraph(vertices);

        for (int[] test : tests) {
            int x = test[0] < 0 ? -test[0] + n : test[0] - 1;
            int y = test[1] < 0 ? -test[1] + n : test[1] - 1;

            implicationGraph.addEdge(x, y); // Add implication x -> y
            implicationGraph.addEdge(y ^ 1, x ^ 1); // Add implication ~y -> ~x
        }

        return implicationGraph;
    }

    private static String arrayToString(int[] array, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(array[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
