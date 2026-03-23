import java.util.*;

public class q2 {
    
    static int[] solve(int n, long[] a) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int less = 0, greater = 0;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) less++;
                else if (a[j] > a[i]) greater++;
            }
            ans[i] = Math.max(less, greater);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextLong();
            int[] ans = solve(n, a);
            for (int i = 0; i < n; i++) {
                sb.append(ans[i]);
                if (i < n - 1) sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}