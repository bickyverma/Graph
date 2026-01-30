import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class blackslexshowing {
     public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        
        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextInt();
            }
        
            long total = 0;
            for (int i = 0; i < n - 1; i++) {
                total += Math.abs(a[i] - a[i + 1]);
            }
            
            long ans = total;
            ans = Math.min(ans, total - Math.abs(a[0] - a[1]));
            
            ans = Math.min(ans, total - Math.abs(a[n - 2] - a[n - 1]));
            
            for (int i = 1; i < n - 1; i++) {
                long removed = Math.abs(a[i - 1] - a[i]) + Math.abs(a[i] - a[i + 1]);
                long added = Math.abs(a[i - 1] - a[i + 1]);
                long newTotal = total - removed + added;
                ans = Math.min(ans, newTotal);
            }
            
            out.append(ans).append('\n');
        }
        
        System.out.print(out.toString());
    }
     static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = read();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}

