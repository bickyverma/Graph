import java.util.*;

public class tree {

    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;
        parent[px] = py;
        return true;
    }

    static boolean isValidReachability(int n, int[][] R) {
        for (int i = 0; i < n; i++)
            if (R[i][i] != 1) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && R[i][j] == 1 && R[j][i] == 1) return false;
        return true;
    }

    static List<int[]> findDirectEdges(int n, int[][] R) {
        List<int[]> edges = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (u == v || R[u][v] != 1) continue;
                boolean isDirect = true;
                for (int w = 0; w < n; w++) {
                    if (w == u || w == v) continue;
                    if (R[u][w] == 1 && R[w][v] == 1) {
                        isDirect = false;
                        break;
                    }
                }
                if (isDirect) edges.add(new int[]{u, v});
            }
        }
        return edges;
    }

    static boolean isConnectedTree(int n, List<int[]> edges) {
        if (edges.size() != n - 1) return false;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] e : edges)
            if (!union(e[0], e[1])) return false;
        int root = find(0);
        for (int i = 1; i < n; i++)
            if (find(i) != root) return false;
        return true;
    }

    static boolean reachabilityMatches(int n, List<int[]> edges, int[][] R) {
        boolean[][] reach = new boolean[n][n];
        for (int i = 0; i < n; i++) reach[i][i] = true;
        for (int[] e : edges) reach[e[0]][e[1]] = true;
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (reach[i][k] && reach[k][j]) reach[i][j] = true;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if ((reach[i][j] ? 1 : 0) != R[i][j]) return false;
        return true;
    }

    static String solve(int n, int[][] R) {
        if (!isValidReachability(n, R)) return "No";

        List<int[]> edges = findDirectEdges(n, R);

        if (!isConnectedTree(n, edges)) return "No";

        if (!reachabilityMatches(n, edges, R)) return "No";

        StringBuilder result = new StringBuilder("Yes\n");
        for (int[] e : edges)
            result.append((e[0] + 1)).append(" ").append((e[1] + 1)).append("\n");
        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder output = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] R = new int[n][n];
            for (int i = 0; i < n; i++) {
                String row = sc.next();
                for (int j = 0; j < n; j++)
                    R[i][j] = row.charAt(j) - '0';
            }
            output.append(solve(n, R)).append("\n");
        }
        System.out.print(output);
    }
}