import java.io.*;
import java.util.*;

public class Main {

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int N = in.nextInt();
        int S = in.nextInt();
        int L = in.nextInt();

        int M = in.nextInt();
        int K = in.nextInt();
        int P = in.nextInt();

        int sp = S / P;
        int R = N * sp * K;
        int oxcPerPlane = M / P;

        int[][] oxc = new int[M][R];
        for (int i = 0; i < M; i++)
            Arrays.fill(oxc[i], -1);

        for (int qi = 0; qi < 5; qi++) {
            int Q = in.nextInt();
            int[][] flows = new int[Q][4];

            for (int i = 0; i < Q; i++) {
                flows[i][0] = in.nextInt(); // gA
                flows[i][1] = in.nextInt();
                flows[i][2] = in.nextInt(); // gB
                flows[i][3] = in.nextInt();
            }

            // Reset topology for this query
            for (int i = 0; i < M; i++)
                Arrays.fill(oxc[i], -1);

            int[] usedCount = new int[M];

            // Build OXC connections
            for (int i = 0; i < Q; i++) {
                int gA = flows[i][0];
                int gB = flows[i][2];

                int spineA = 0;
                int spineB = 0;
                int link = 0;

                int plane = spineA / sp;
                int oxcId = plane * oxcPerPlane;

                int portA = gA * sp * K + (spineA % sp) * K + link;
                int portB = gB * sp * K + (spineB % sp) * K + link;

                if (oxc[oxcId][portA] == -1 && oxc[oxcId][portB] == -1) {
                    oxc[oxcId][portA] = portB;
                    oxc[oxcId][portB] = portA;
                }
            }

            // Output topology
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < R; j++) {
                    out.print(oxc[i][j]);
                    if (j + 1 < R) out.print(' ');
                }
                out.println();
            }

            // Output routing
            for (int i = 0; i < Q; i++) {
                int spineA = 0;
                int spineB = 0;
                int plane = spineA / sp;
                int oxcId = plane * oxcPerPlane;

                out.println(spineA + " 0 " + oxcId + " " + spineB + " 0");
            }
        }

        out.flush();
    }
}
