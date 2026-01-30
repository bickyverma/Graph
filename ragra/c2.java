import java.io.*;
import java.util.*;

public class c2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(" ");
            
            int[] a = new int[n];
            int[] b = new int[n];
            
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
                b[i] = a[i];
            }
            
            Arrays.sort(b);
            
            int ans = Integer.MAX_VALUE;
            boolean mismatch = false;
            
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) {
                    mismatch = true;
                    ans = Math.min(ans, Math.abs(a[i] - b[i]));
                }
            }
            
            if (!mismatch) sb.append(-1).append("\n");
            else sb.append(ans).append("\n");
        }
        
        System.out.print(sb);
    }
}
