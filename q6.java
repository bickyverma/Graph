import java.io.*;
import java.util.*;

public class q6{

    static int n, k;
    static long[] a, S;
    static int[] head, nxt, to;
    static int edgeCnt;
    static boolean[] isLeaf;
    static int[] downDist, downId, upDist, upId, best1, best2, bestNeigh, par, bfsOrder;
    static long ans;
    static TopKSum topK;
    static FastReader in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(null, q6::run, "main", 1 << 26);
        thread.start();
        thread.join();
    }

    static void run() {
        try {
            in = new FastReader();
            out = new PrintWriter(new BufferedOutputStream(System.out));
            int t = in.nextInt();
            while (t-- > 0) solve();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void addEdge(int u, int v) {
        to[edgeCnt] = v; nxt[edgeCnt] = head[u]; head[u] = edgeCnt++;
        to[edgeCnt] = u; nxt[edgeCnt] = head[v]; head[v] = edgeCnt++;
    }

    static int destOf(int u, int parentU) {
        if (isLeaf[u]) return u;
        return (bestNeigh[u] == parentU) ? best2[u] : best1[u];
    }

    static void shift(int u, int parentU, long delta) {
        int dest = destOf(u, parentU);
        topK.remove(dest, S[dest]);
        S[dest] += delta;
        topK.add(dest, S[dest]);
    }

    static void solve() {
        n = in.nextInt();
        k = in.nextInt();

        a = new long[n + 1];
        for (int i = 1; i <= n; i++) a[i] = in.nextLong();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        nxt = new int[2 * n];
        to  = new int[2 * n];
        edgeCnt = 0;

        for (int i = 0; i < n - 1; i++) addEdge(in.nextInt(), in.nextInt());

        if (n == 1) { out.println(a[1]); return; }

        isLeaf    = new boolean[n + 1];
        int[] deg = new int[n + 1];
        for (int u = 1; u <= n; u++)
            for (int e = head[u]; e != -1; e = nxt[e]) deg[u]++;
        for (int i = 1; i <= n; i++) isLeaf[i] = (deg[i] == 1);

        downDist  = new int[n + 1];
        downId    = new int[n + 1];
        upDist    = new int[n + 1];
        upId      = new int[n + 1];
        best1     = new int[n + 1];
        best2     = new int[n + 1];
        bestNeigh = new int[n + 1];
        par       = new int[n + 1];
        S         = new long[n + 1];
        bfsOrder  = new int[n];

        int[] queue   = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        int hd = 0, tl = 0, oi = 0;
        queue[tl++] = 1;
        vis[1] = true;
        par[1] = 0;

        while (hd < tl) {
            int u = queue[hd++];
            bfsOrder[oi++] = u;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (!vis[v]) {
                    vis[v] = true;
                    par[v] = u;
                    queue[tl++] = v;
                }
            }
        }

        for (int i = oi - 1; i >= 0; i--) {
            int u = bfsOrder[i], p = par[u];
            if (isLeaf[u]) { downDist[u] = 0; downId[u] = u; continue; }
            downDist[u] = -1; downId[u] = -1;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e]; if (v == p) continue;
                int cd = downDist[v] + 1, ci = downId[v];
                if (cd > downDist[u] || (cd == downDist[u] && ci < downId[u])) {
                    downDist[u] = cd; downId[u] = ci;
                }
            }
        }

        for (int i = 0; i < oi; i++) {
            int u = bfsOrder[i], p = par[u];
            int m1d = -1, m1i = -1, m1n = -1;
            int m2d = -1, m2i = -1, m2n = -1;

            if (u != 1) { m1d = upDist[u]; m1i = upId[u]; m1n = p; }

            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e]; if (v == p) continue;
                int d = downDist[v] + 1, id = downId[v];
                if (d > m1d || (d == m1d && id < m1i)) {
                    m2d = m1d; m2i = m1i; m2n = m1n;
                    m1d = d;   m1i = id;  m1n = v;
                } else if (d > m2d || (d == m2d && id < m2i)) {
                    m2d = d; m2i = id; m2n = v;
                }
            }

            if (!isLeaf[u]) { best1[u] = m1i; bestNeigh[u] = m1n; best2[u] = m2i; }

            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e]; if (v == p) continue;
                int bed, bei;
                if (m1n == v) {
                    bed = (m2n != -1) ? m2d : 0;
                    bei = (m2n != -1) ? m2i : u;
                } else {
                    bed = m1d; bei = m1i;
                }
                upDist[v] = bed + 1;
                upId[v]   = bei;
            }
        }

        topK = new TopKSum(k - 1);
        for (int i = 1; i <= n; i++) if (isLeaf[i]) topK.add(i, 0);

        for (int u = 2; u <= n; u++) {
            int dest = destOf(u, par[u]);
            topK.remove(dest, S[dest]);
            S[dest] += a[u];
            topK.add(dest, S[dest]);
        }

        ans = a[1] + topK.sumTop;

        int maxOps    = 4 * n + 10;
        int[] opType  = new int[maxOps];
        int[] opU     = new int[maxOps];
        int[] opV     = new int[maxOps];
        int top = 0;

        for (int e = head[1]; e != -1; e = nxt[e]) {
            int v = to[e];
            opType[top] = -1; opU[top] = 1; opV[top] = v; top++;
            opType[top] =  1; opU[top] = 1; opV[top] = v; top++;
        }

        while (top > 0) {
            top--;
            int type = opType[top], u = opU[top], v = opV[top];

            if (type == 1) {
                shift(u, v,  a[u]);
                shift(v, u, -a[v]);
                ans = Math.max(ans, a[v] + topK.sumTop);
                for (int e = head[v]; e != -1; e = nxt[e]) {
                    int w = to[e]; if (w == u) continue;
                    opType[top] = -1; opU[top] = v; opV[top] = w; top++;
                    opType[top] =  1; opU[top] = v; opV[top] = w; top++;
                }
            } else {
                shift(v, u,  a[v]);
                shift(u, v, -a[u]);
            }
        }

        out.println(ans);
    }

    static class TopKSum {
        int K;
        long sumTop;
        TreeSet<long[]> topK, rest;
        static final Comparator<long[]> CMP =
            (x, y) -> x[0] != y[0] ? Long.compare(x[0], y[0]) : Long.compare(x[1], y[1]);

        TopKSum(int k) {
            K = k; sumTop = 0;
            topK = new TreeSet<>(CMP);
            rest = new TreeSet<>(CMP);
        }

        void add(int id, long val) {
            rest.add(new long[]{val, id});
            balance();
        }

        void remove(int id, long val) {
            long[] p = {val, id};
            if (topK.contains(p)) { topK.remove(p); sumTop -= val; }
            else rest.remove(p);
            balance();
        }

        void balance() {
            while (topK.size() < K && !rest.isEmpty()) {
                long[] p = rest.last(); rest.remove(p); topK.add(p); sumTop += p[0];
            }
            while (topK.size() > K) {
                long[] p = topK.first(); topK.remove(p); sumTop -= p[0]; rest.add(p);
            }
            while (!topK.isEmpty() && !rest.isEmpty()) {
                long[] lo = topK.first(), hi = rest.last();
                if (CMP.compare(lo, hi) < 0) {
                    topK.remove(lo); sumTop -= lo[0];
                    rest.remove(hi);
                    topK.add(hi);    sumTop += hi[0];
                    rest.add(lo);
                } else break;
            }
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }

        String next() {
            while (st == null || !st.hasMoreElements())
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            return st.nextToken();
        }

        int nextInt()   { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
    }
}