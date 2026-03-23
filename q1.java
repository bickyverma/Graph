import java.util.*;

public class q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            long c = sc.nextLong();
            long k = sc.nextLong();
            
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }
            
            Arrays.sort(a);
            
            for (int i = 0; i < n; i++) {
                if (a[i] <= c) {
                    long flip = Math.min(k, c - a[i]);
                    k = k - flip;
                    c  = c + (a[i] + flip);
                } else {
                    break; 
                }
            }
            
            System.out.println(c);
        }
        
        sc.close();
    }
}