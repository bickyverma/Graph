import java.util.*;
import java.io.*;

public class q5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            char[] s = br.readLine().toCharArray();

            while (q-- > 0) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                out.append(solve(s, l, r)).append('\n');
            }
        }

        System.out.print(out);
    }

    static long solve(char[] s, int l, int r) {
        int m = r - l + 1;
        char[] t = Arrays.copyOfRange(s, l, r + 1);
        int[] Z = buildZ(t, m);

        int[] dp = new int[m + 1];
        int[] deque = new int[m];
        int head = 0, tail = 0;
        long sum = 0;

        for (int i = 1; i <= m; i++) {
            int j = i - 1;
            if (Z[j] >= 1) {
                deque[tail++] = j;
                dp[i] = dp[i - 1] + 1;
            } else {
                while (head < tail && deque[tail - 1] + Z[deque[tail - 1]] < i) tail--;
                dp[i] = (head < tail) ? dp[deque[tail - 1]] + 1 : 0;
            }
            sum += dp[i];
        }

        return sum;
    }

    static int[] buildZ(char[] t, int m) {
        int[] Z = new int[m];
        Z[0] = m;
        int l = 0, r = 0;
        for (int i = 1; i < m; i++) {
            if (i < r) Z[i] = Math.min(r - i, Z[i - l]);
            while (i + Z[i] < m && t[Z[i]] == t[i + Z[i]]) Z[i]++;
            if (i + Z[i] > r) { l = i; r = i + Z[i]; }
        }
        return Z;
    }
}