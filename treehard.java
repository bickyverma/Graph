import java.util.*;
import java.io.*;

public class treehard {
    static int n;
    static byte[][] R;
    static int[] outDeg;

    static boolean isBasicValid() {
        for (int i = 0; i < n; i++) {
            if (R[i][i] == 0) return false;
        }
        return true;
    }

    static List<int[]> findEdges() {
        List<int[]> edges = new ArrayList<>();
        
        // Sort nodes by out-degree descending
        Integer[] nodes = new Integer[n];
        for (int i = 0; i < n; i++) nodes[i] = i;
        Arrays.sort(nodes, (a, b) -> Integer.compare(outDeg[b], outDeg[a]));

        int[] covered = new int[n];
        int timer = 0;

        // O(N^2) greedy edge assignment
        for (int u = 0; u < n; u++) {
            timer++; // Use a timer to avoid re-allocating the 'covered' array
            
            for (int i = 0; i < n; i++) {
                int v = nodes[i];
                
                // If u reaches v, and v is not already covered by a larger child
                if (u != v && R[u][v] == 1 && covered[v] != timer) {
                    edges.add(new int[]{u, v});
                    if (edges.size() > n - 1) return edges; // Early exit if too many edges
                    
                    // Mark all descendants of v as covered so u doesn't link to them
                    for (int w = 0; w < n; w++) {
                        if (R[v][w] == 1) {
                            covered[w] = timer;
                        }
                    }
                }
            }
        }
        return edges;
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    static boolean isTree(List<int[]> edges) {
        if (edges.size() != n - 1) return false;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] e : edges) {
            int pu = find(parent, e[0]), pv = find(parent, e[1]);
            if (pu == pv) return false;
            parent[pu] = pv;
        }
        int root = find(parent, 0);
        for (int i = 1; i < n; i++) if (find(parent, i) != root) return false;
        return true;
    }

    // A much faster O(N^2) DFS to verify exact reachability
    static boolean verifyReachability(List<int[]> edges) {
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] to = new int[n - 1];
        int[] next = new int[n - 1];

        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i)[0];
            int v = edges.get(i)[1];
            to[i] = v;
            next[i] = head[u];
            head[u] = i;
        }

        int[] vis = new int[n];
        int timer = 0;

        for (int u = 0; u < n; u++) {
            timer++;
            int reachedCount = dfs(u, u, head, next, to, vis, timer);
            if (reachedCount != outDeg[u]) return false;
        }
        return true;
    }

    static int dfs(int start, int curr, int[] head, int[] next, int[] to, int[] vis, int timer) {
        vis[curr] = timer;
        if (R[start][curr] == 0) return -1; // DFS found a path that shouldn't exist
        
        int count = 1;
        for (int e = head[curr]; e != -1; e = next[e]) {
            int v = to[e];
            if (vis[v] != timer) {
                int sub = dfs(start, v, head, next, to, vis, timer);
                if (sub == -1) return -1;
                count += sub;
            }
        }
        return count;
    }

    static String solve() {
        if (!isBasicValid()) return "No";
        List<int[]> edges = findEdges();
        if (!isTree(edges)) return "No";
        if (!verifyReachability(edges)) return "No";

        StringBuilder sb = new StringBuilder("Yes\n");
        for (int[] e : edges)
            sb.append(e[0] + 1).append(' ').append(e[1] + 1).append('\n');
        return sb.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tStr = br.readLine();
        if (tStr == null) return;
        int t = Integer.parseInt(tStr.trim());
        StringBuilder out = new StringBuilder();

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine().trim());
            R = new byte[n][n];
            outDeg = new int[n];

            for (int i = 0; i < n; i++) {
                String row = br.readLine().trim();
                for (int j = 0; j < n; j++) {
                    R[i][j] = (byte) (row.charAt(j) - '0');
                    if (R[i][j] == 1) outDeg[i]++;
                }
            }

            out.append(solve()).append('\n');
        }

        System.out.print(out);
    }
}