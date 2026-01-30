import java.util.*;
import java.io.*;

public class fs {  
 public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            long[] a = new long[n];

            long total = 0;
            for (int i = 0; i < n; i++) {
                a[i] = fs.nextLong();
                total += a[i];
            }

            long pref = 0;
            long ans = Long.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                long cur = 2 * pref + a[i] - total;
                ans = Math.max(ans, cur);
                pref += a[i];
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
    }

    // Fast input
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
                if (len == -1) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') ;
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ') ;
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}